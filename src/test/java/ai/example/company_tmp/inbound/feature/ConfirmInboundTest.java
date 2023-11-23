package ai.example.company_tmp.inbound.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import ai.example.company_tmp.inbound.domain.InboundStatus;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ConfirmInboundTest extends ApiTest {

    @Autowired
    private InboundRepository inboundRepository;

    @Test
    @DisplayName("입고 승인")
    void confirmInbound() {
        Scenario.registerProduct().request()
                .registerInbound().request();

        final Long inboundNo = 1L;

        RestAssured.given().log().all()
                   .contentType(MediaType.APPLICATION_JSON_VALUE)
                   .when()
                   .post("/inbounds/{inboundNo}/confirm", inboundNo)
                   .then().log().all()
                   .statusCode(HttpStatus.OK.value());

        final Inbound inbound = inboundRepository.getBy(inboundNo);
        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.CONFIRMED);
    }

}
