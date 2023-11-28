package ai.example.company_tmp.inbound.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

class RegisterLPNTest extends ApiTest {

    @Autowired
    private RegisterLPN registerLPN;
    @Autowired
    private InboundRepository inboundRepository;

    @Test
    @DisplayName("LPN 등록")
    @Transactional
    void registerLPN() {
        Scenario
            .registerProduct().request()
            .registerInbound().request()
            .confirmInbound().request()
            .registerLPN().request();

        final Long inboundItemNo = 1L;

        final Inbound inbound = inboundRepository.findByInboundItemNo(inboundItemNo).get();
        assertThat(inbound.testingGetInboundItemBy(inboundItemNo).testingGetLpnList()).hasSize(1);
    }


}
