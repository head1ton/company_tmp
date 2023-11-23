package ai.example.company_tmp.inbound.feature.api;

import ai.example.company_tmp.common.Scenario;
import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ConfirmInboundApi {

    private Long inboundNo = 1L;

    public ConfirmInboundApi inboundNo(final Long inboundNo) {
        this.inboundNo = inboundNo;
        return this;
    }

    public Scenario request() {
        RestAssured.given().log().all()
                   .contentType(MediaType.APPLICATION_JSON_VALUE)
                   .when()
                   .post("/inbounds/{inboundNo}/confirm", inboundNo)
                   .then().log().all()
                   .statusCode(HttpStatus.OK.value());

        return new Scenario();
    }
}
