package ai.example.company_tmp.inbound.feature;

import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConfirmInboundTest {

    private ConfirmInbound confirmInbound;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        confirmInbound = new ConfirmInbound();
    }

    @Test
    @DisplayName("입고 승인")
    void confirmInbound() {
        final Long inboundNo = 1L;

        confirmInbound.request(inboundNo);

//        assertThat(inboundRepository.findById(inboundNo).get().getStatus()).isEqualTo("");
    }

    private class ConfirmInbound {

        public void request(final Long inboundNo) {
            final Inbound inbound = inboundRepository.findById(inboundNo).orElseThrow();

            inbound.confirmed();
        }
    }
}
