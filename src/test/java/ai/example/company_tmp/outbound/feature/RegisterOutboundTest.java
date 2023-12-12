package ai.example.company_tmp.outbound.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.outbound.domain.OutboundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RegisterOutboundTest extends ApiTest {

    @Autowired
    private OutboundRepository outboundRepository;

    @BeforeEach
    void setUpRegisterOutbound() {
        Scenario
            .registerProduct().request();
        Scenario    // 상품 생성
                    .registerInbound().request();
        Scenario    // 입고 생성
                    .confirmInbound().request()     // 입고 승인
                    .registerLPN().request()            // lpn 생성
                    .registerLocation().request()   // 로케이션 추가
                    .registerPackingMaterial().request();
        Scenario // 포장재
                 .assignInventory().request();   // 재고 생성
    }

    @Test
    @DisplayName("출고를 등록한다.")
    void registerOutbound() {
        Scenario
            .registerOutbound().request();  // 출고 등록

        assertThat(outboundRepository.findAll()).hasSize(1);
    }
}
