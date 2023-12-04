package ai.example.company_tmp.location.feature.api;

import ai.example.company_tmp.location.feature.AssignLocationLPN.Request;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

public class AssignLocationLPNApi {

    public static String assignLocationLPN() {
        final String locationBarcode = "A-1-1";
        final String lpnBarcode = "LPN-0001";
        final Request request = new Request(
            locationBarcode,
            lpnBarcode
        );

        RestAssured
            .given().log().all()
            .contentType(ContentType.JSON)
            .body(request)
            .when()
            .post("/locations/assign-lpn")
            .then().log().all()
            .statusCode(HttpStatus.OK.value());
        return locationBarcode;
    }
}