package ai.example.company_tmp.outbound.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.outbound.domain.OutboundRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RegisterOutboundTest extends ApiTest {

    @Autowired
    private OutboundRepository outboundRepository;

    @Test
    @DisplayName("출고를 등록한다.")
    void registerOutbound() {
        Scenario.registerProduct().request();
        Scenario
            .registerOutbound().request();

        assertThat(outboundRepository.findAll()).hasSize(1);
    }
}
