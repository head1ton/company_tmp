package ai.example.company_tmp.product.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.product.domain.Category;
import ai.example.company_tmp.product.domain.ProductRepository;
import ai.example.company_tmp.product.domain.TemperatureZone;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RegisterProductTest {

    @LocalServerPort
    private int port;

    private RegisterProduct registerProduct;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        if (RestAssured.UNDEFINED_PORT == RestAssured.port) {
            RestAssured.port = port;
        }
    }

    @Test
    @DisplayName("상품 등록")
    void registerProduct() {
        Long weightInGrams = 1000L;
        Long widthInMillimeters = 100L;
        Long heightInMillimeters = 100L;
        Long lengthInMillimeters = 100L;
        String name = "name";
        String code = "code";
        String description = "description";
        String brand = "brand";
        String maker = "maker";
        String origin = "origin";
        Category category = Category.ELECTRONICS;
        TemperatureZone temperatureZone = TemperatureZone.ROOM_TEMPERATURE;
        RegisterProduct.Request request = new RegisterProduct.Request(
            name,
            code,
            description,
            brand,
            maker,
            origin,
            category,
            temperatureZone,
            weightInGrams,
            widthInMillimeters,
            heightInMillimeters,
            lengthInMillimeters
        );
//        registerProduct.request(request);

        RestAssured.given().log().all()
                   .contentType(ContentType.JSON)
                   .body(request)
                   .when()
                   .post("/products")
                   .then().log().all()
                   .statusCode(HttpStatus.CREATED.value());

        assertThat(productRepository.findAll()).hasSize(1);
    }

}
