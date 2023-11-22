package ai.example.company_tmp.inbound.feature.api;

import ai.example.company_tmp.inbound.feature.RegisterInbound;
import ai.example.company_tmp.inbound.feature.RegisterInbound.Request.Item;
import ai.example.company_tmp.product.common.Scenario;
import io.restassured.RestAssured;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class RegisterInboundApi {

    private String title = "title";
    private String description = "description";
    private LocalDateTime orderRequestedAt = LocalDateTime.now();
    private LocalDateTime estimatedArrivalAt = LocalDateTime.now().plusDays(1);
    private List<Item> inboundItems = List.of(new Item(
        1L,
        1L,
        1500L,
        "description"
    ));

    public RegisterInboundApi title(final String title) {
        this.title = title;
        return this;
    }

    public RegisterInboundApi description(final String description) {
        this.description = description;
        return this;
    }

    public RegisterInboundApi orderRequestedAt(final LocalDateTime orderRequestedAt) {
        this.orderRequestedAt = orderRequestedAt;
        return this;
    }

    public RegisterInboundApi estimatedArrivalAt(final LocalDateTime estimatedArrivalAt) {
        this.estimatedArrivalAt = estimatedArrivalAt;
        return this;
    }

    public RegisterInboundApi inboundItems(final RegisterInbound.Request.Item... inboundItems) {
        this.inboundItems = List.of(inboundItems);
        return this;
    }

    public Scenario request() {

        final RegisterInbound.Request request = new RegisterInbound.Request(
            title,
            description,
            orderRequestedAt,
            estimatedArrivalAt,
            inboundItems
        );

        RestAssured.given().log().all()
                   .contentType(MediaType.APPLICATION_JSON_VALUE)
                   .body(request)
                   .when()
                   .post("/inbounds")
                   .then().log().all()
                   .statusCode(HttpStatus.CREATED.value());

        return new Scenario();
    }
}
