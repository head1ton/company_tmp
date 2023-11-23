package ai.example.company_tmp.inbound.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import ai.example.company_tmp.inbound.domain.InboundStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfirmInboundTest extends ApiTest {

    @Autowired
    private InboundRepository inboundRepository;

    @Test
    @DisplayName("입고 승인")
    void confirmInbound() {
        final Long inboundNo = 1L;

        Scenario.registerProduct().request()
                .registerInbound().request()
                .confirmInbound().inboundNo(inboundNo).request();

        final Inbound inbound = inboundRepository.getBy(inboundNo);
        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.CONFIRMED);
    }

}
