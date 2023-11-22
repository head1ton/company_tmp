package ai.example.company_tmp.inbound.feature;

import static ai.example.company_tmp.product.fixture.ProductFixture.aProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyLong;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import ai.example.company_tmp.product.domain.ProductRepository;
import io.restassured.RestAssured;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class RegisterInboundTest extends ApiTest {

    private RegisterInbound registerInbound;
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private InboundRepository inboundRepository;

    @Test
    @DisplayName("입고 등록")
    void registerInbound() {

        Mockito.when(productRepository.getBy(anyLong()))
               .thenReturn(aProduct().build());

        final LocalDateTime orderRequestedAt = LocalDateTime.now();
        final LocalDateTime estimatedArrivalAt = LocalDateTime.now().plusDays(1);
        final Long productNo = 1L;
        final Long quantity = 1L;
        final Long unitPrice = 1500L;
        final RegisterInbound.Request.Item inboundItem = new RegisterInbound.Request.Item(
            productNo,
            quantity,
            unitPrice,
            "description"
        );
        final List<RegisterInbound.Request.Item> inboundItems = List.of(inboundItem);

        final RegisterInbound.Request request = new RegisterInbound.Request(
            "title",
            "description",
            orderRequestedAt,
            estimatedArrivalAt,
            inboundItems
        );
//        registerInbound.request(request);

        RestAssured.given().log().all()
                   .contentType(MediaType.APPLICATION_JSON_VALUE)
                   .body(request)
                   .when()
                   .post("/inbounds")
                   .then().log().all()
                   .statusCode(HttpStatus.CREATED.value());

        assertThat(inboundRepository.findAll()).hasSize(1);

    }

}
