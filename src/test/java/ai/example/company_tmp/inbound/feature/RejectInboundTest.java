package ai.example.company_tmp.inbound.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundFixture;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import ai.example.company_tmp.inbound.domain.InboundStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RejectInboundTest {

    private RejectInbound rejectInbound;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        inboundRepository = Mockito.mock(InboundRepository.class);
        rejectInbound = new RejectInbound(inboundRepository);
    }

    @Test
    @DisplayName("입고를 반려/거부")
    void rejectInbound() {
        final Long inboundNo = 1L;
        final String rejectionReason = "반려 사유";

        final RejectInbound.Request request = new RejectInbound.Request(rejectionReason);

        final Inbound inbound = InboundFixture.anInbound().build();
        Mockito.when(inboundRepository.getBy(inboundNo))
               .thenReturn(inbound);

        rejectInbound.request(inboundNo, request);

        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.REJECTED);
    }

}
