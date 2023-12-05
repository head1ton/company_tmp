package ai.example.company_tmp.location.feature.api;

import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.location.feature.AssignInventory.Request;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

public class AssignInventoryApi {

    private String locationBarcode = "A-1-1";
    private String lpnBarcode = "LPN-0001";

    public AssignInventoryApi locationBarcode(final String locationBarcode) {
        this.locationBarcode = locationBarcode;
        return this;
    }

    public AssignInventoryApi lpnBarcode(final String lpnBarcode) {
        this.lpnBarcode = lpnBarcode;
        return this;
    }

    public Scenario build() {
        final Request request = new Request(
            locationBarcode,
            lpnBarcode
        );

        RestAssured
            .given().log().all()
            .contentType(ContentType.JSON)
            .body(request)
            .when()
            .post("/locations/assign-inventory")
            .then().log().all()
            .statusCode(HttpStatus.OK.value());

        return new Scenario();
    }
}