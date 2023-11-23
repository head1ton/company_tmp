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

public class ConfirmInboundTest {

    private ConfirmInbound confirmInbound;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        inboundRepository = Mockito.mock(InboundRepository.class);
        confirmInbound = new ConfirmInbound(inboundRepository);
    }

    @Test
    @DisplayName("입고 승인")
    void confirmInbound() {
        final Long inboundNo = 1L;

        final Inbound inbound = InboundFixture.anInbound().build();

        Mockito.when(inboundRepository.getBy(inboundNo))
               .thenReturn(inbound);

        confirmInbound.request(inboundNo);

        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.CONFIRMED);
    }

}
