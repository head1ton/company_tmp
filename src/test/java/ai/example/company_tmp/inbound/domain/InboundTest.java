package ai.example.company_tmp.inbound.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

class InboundTest {

    @Test
    @DisplayName("입고 승인 상태")
    void confirmed() {
        final Inbound inbound = InboundFixture.anInbound().build();
        final InboundStatus beforeStatus = inbound.getStatus();

        inbound.confirmed();

        assertThat(beforeStatus).isEqualTo(InboundStatus.REQUESTED);
        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.CONFIRMED);
    }

    @Test
    @DisplayName("입고 승인 상태 실패")
    void fail_invalid_status_confirmed() {
        final Inbound confirmedInbound = InboundFixture.anInboundWithConfirmed().build();
//        final Inbound confirmedInbound = InboundFixture.anInbound()
//            .inboundStatus(InboundStatus.CONFIRMED).build();

        assertThatThrownBy(confirmedInbound::confirmed).isInstanceOf(IllegalStateException.class)
                                              .hasMessageContaining("입고 요청 상태가 아닙니다.");
    }

    @Test
    @DisplayName("입고 반려/거부하면 입고의 상태가 REJECTED가 된다.")
    void reject() {
        final Inbound inbound = InboundFixture.anInbound().build();
        final InboundStatus beforeStatus = inbound.getStatus();

        final String rejectionReason = "반려 사유";
        inbound.reject(rejectionReason);

        assertThat(beforeStatus).isEqualTo(InboundStatus.REQUESTED);
        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.REJECTED);
    }

    @Test
    @DisplayName("입고 승인 상태 실패 - 입고의 상태가 요청이 아닌 경우 예외가 발생한다.")
    void fail_invalid_status_reject() {
        final Inbound confirmInbound = InboundFixture.anInboundWithConfirmed().build();

        final String rejectionReason = "반려 사유";
        assertThatThrownBy(() -> confirmInbound.reject(rejectionReason)).isInstanceOf(
                                                                            IllegalStateException.class)
                                                                        .hasMessageContaining(
                                                                            "입고 요청 상태가 아닙니다.");
    }

    @Test
    @DisplayName("LPN 등록")
    void registerLPN() {
        final Inbound inbound = InboundFixture.anInboundWithConfirmed().build();  // 입고 확정 상태만.
        final Long inboundItemNo = 1L;
        final String lpnBarcode = "LPN-0001";
        final LocalDateTime expirationAt = LocalDateTime.now().plusDays(1);

        inbound.registerLPN(inboundItemNo, lpnBarcode, expirationAt);

        final InboundItem inboundItem = inbound.testingGetInboundItemBy(inboundItemNo);
        final List<LPN> lpns = inboundItem.testingGetLpnList();

        assertThat(lpns).hasSize(1);
    }

    @Test
    @DisplayName("LPN 등록 실패 - 입고 확정 상태가 아니라면 예외가 발생한다.")
    @Transactional
    void fail_invalid_registerLPN() {

        final Inbound inbound = InboundFixture.anInbound().build();
        final Long inboundItemNo = 1L;
        final String lpnBarcode = "LPN-0001";
        final LocalDateTime expirationAt = LocalDateTime.now().plusDays(1);

        assertThatThrownBy(() -> {
            inbound.registerLPN(inboundItemNo, lpnBarcode, expirationAt);
        }).isInstanceOf(IllegalStateException.class)
          .hasMessageContaining("입고 확정 상태가 아닙니다.");
    }

    @Test
    @DisplayName("LPN 등록 실패 - 유통기한 예외 발생")
    @Transactional
    void fail_expire_registerLPN() {

        final Inbound inbound = InboundFixture.anInboundWithConfirmed().build();
        final Long inboundItemNo = 1L;
        final String lpnBarcode = "LPN-0001";
        final LocalDateTime expirationAt = LocalDateTime.now();

        assertThatThrownBy(() -> {
            inbound.registerLPN(inboundItemNo, lpnBarcode, expirationAt);
        }).isInstanceOf(IllegalStateException.class)
          .hasMessageContaining("유통기한은 현재 시간보다 이전 일 수 없습니다.");
    }

}