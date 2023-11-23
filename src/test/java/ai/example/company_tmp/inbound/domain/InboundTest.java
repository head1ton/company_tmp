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

}