package ai.example.company_tmp.inbound.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}