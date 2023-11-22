package ai.example.company_tmp.inbound.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import ai.example.company_tmp.product.common.Scenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RegisterInboundTest extends ApiTest {

    @Autowired
    private InboundRepository inboundRepository;

    @Test
    @DisplayName("입고 등록")
    void registerInbound() {

        Scenario.registerProduct().request()
                .registerInbound().request();

        assertThat(inboundRepository.findAll()).hasSize(1);

    }

}
