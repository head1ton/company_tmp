package ai.example.company_tmp.inbound.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            .confirmInbound().request();

        final Long inboundItemNo = 1L;
        final String lpnBarcode = "LPN-0001";
        final LocalDateTime expirationAt = LocalDateTime.now().plusDays(1);

        final RegisterLPN.Request request = new RegisterLPN.Request(
            lpnBarcode,
            expirationAt
        );

        RestAssured.given().log().all()
                   .contentType(ContentType.JSON)
                   .body(request)
                   .when()
                   .post("/inbounds/inbound-items/{inboundItemNo}/lpn", inboundItemNo)
                   .then().log().all()
                   .statusCode(HttpStatus.OK.value());

//        registerLPN.request(inboundItemNo, request);

        final Inbound inbound = inboundRepository.findByInboundItemNo(inboundItemNo).get();
        assertThat(inbound.testingGetInboundItemBy(inboundItemNo).testingGetLpnList()).hasSize(1);
    }

}
