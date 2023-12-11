package ai.example.company_tmp.outbound.feature.api;

import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.outbound.feature.RegisterOutbound;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalDate;
import org.springframework.http.HttpStatus;

public class RegisterOutboundApi {

    private Long orderNo = 1L;
    private Boolean isPriorityDelivery = false;
    private LocalDate desiredDeliveryAt = LocalDate.now();

    public RegisterOutboundApi orderNo(final Long orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public RegisterOutboundApi isPriorityDelivery(final Boolean isPriorityDelivery) {
        this.isPriorityDelivery = isPriorityDelivery;
        return this;
    }

    public RegisterOutboundApi desiredDeliveryAt(final LocalDate desiredDeliveryAt) {
        this.desiredDeliveryAt = desiredDeliveryAt;
        return this;
    }

    public Scenario request() {

        final RegisterOutbound.Request request = new RegisterOutbound.Request(
            orderNo,
            isPriorityDelivery,
            desiredDeliveryAt
        );

        RestAssured.given().log().all()
                   .contentType(ContentType.JSON)
                   .body(request)
                   .when()
                   .post("/outbounds")
                   .then().log().all()
                   .statusCode(HttpStatus.CREATED.value());

        return new Scenario();
    }
}
