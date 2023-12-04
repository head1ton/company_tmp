package ai.example.company_tmp.location.fixture;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.location.domain.LocationRepository;
import ai.example.company_tmp.location.domain.StorageType;
import ai.example.company_tmp.location.domain.UsagePurpose;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

class RegisterLocationTest extends ApiTest {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    @DisplayName("로케이션을 등록한다.")
    void registerLocation() {
        final String locationBarcode = "A-1-1";

        final StorageType storageType = StorageType.TOTE;
        final UsagePurpose usagePurpose = UsagePurpose.MOVE;
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

//        registerLocation.request(request);

        assertThat(locationRepository.findAll()).hasSize(1);
    }

}
