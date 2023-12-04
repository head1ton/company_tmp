package ai.example.company_tmp.location.feature.api;

import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.location.domain.StorageType;
import ai.example.company_tmp.location.domain.UsagePurpose;
import ai.example.company_tmp.location.fixture.RegisterLocation;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

public class RegisterLocationApi {

    private String locationBarcode = "A-1-1";
    private StorageType storageType = StorageType.TOTE;
    private UsagePurpose usagePurpose = UsagePurpose.MOVE;

    public RegisterLocationApi locationBarcode(final String locationBarcode) {
        this.locationBarcode = locationBarcode;
        return this;
    }

    public RegisterLocationApi storageType(final StorageType storageType) {
        this.storageType = storageType;
        return this;
    }

    public RegisterLocationApi usagePurpose(final UsagePurpose usagePurpose) {
        this.usagePurpose = usagePurpose;
        return this;
    }

    public Scenario request() {

        final RegisterLocation.Request request = new RegisterLocation.Request(
            locationBarcode,
            storageType,
            usagePurpose
        );

        RestAssured.given().log().all()
                   .contentType(ContentType.JSON)
                   .body(request)
                   .when()
                   .post("/locations")
                   .then().log().all()
                   .statusCode(HttpStatus.CREATED.value());

        return new Scenario();
    }
}
