package ai.example.company_tmp.inbound.feature.api;

import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.inbound.feature.RegisterLPN;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class RegisterLPNApi {

    private Long inboundItemNo = 1L;
    private String lpnBarcode = "LPN-0001";
    private LocalDateTime expirationAt = LocalDateTime.now().plusDays(1);

    public RegisterLPNApi inboundItemNo(final Long inboundItemNo) {
        this.inboundItemNo = inboundItemNo;
        return this;
    }

    public RegisterLPNApi lpnBarcode(final String lpnBarcode) {
        this.lpnBarcode = lpnBarcode;
        return this;
    }

    public RegisterLPNApi expirationAt(final LocalDateTime expirationAt) {
        this.expirationAt = expirationAt;
        return this;
    }

    public Scenario request() {

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

        return new Scenario();
    }
}
