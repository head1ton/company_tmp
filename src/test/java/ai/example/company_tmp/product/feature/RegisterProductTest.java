package ai.example.company_tmp.product.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.product.domain.Category;
import ai.example.company_tmp.product.domain.ProductRepository;
import ai.example.company_tmp.product.domain.TemperatureZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegisterProductTest {

    private RegisterProduct registerProduct;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        registerProduct = new RegisterProduct(productRepository);
    }

    @Test
    @DisplayName("상품 등록")
    void registerProduct() {
        long weightInGrams = 1000L;
        long widthInMillimeters = 100L;
        long heightInMillimeters = 100L;
        long lengthInMillimeters = 100L;
        String name = "name";
        String code = "code";
        String description = "description";
        String brand = "brand";
        String maker = "maker";
        String origin = "origin";
        Category category = Category.ELECTRONICS;
        TemperatureZone temperatureZone = TemperatureZone.ROOM_TEMPERATURE;
        final RegisterProduct.Request request = new RegisterProduct.Request(
            name,
            code,
            description,
            brand,
            maker,
            origin,
            category,
            temperatureZone,
            weightInGrams,  // gram
            widthInMillimeters,   // 너비 mm
            heightInMillimeters,  // 높이 mm
            lengthInMillimeters // 길이 mm
        );
        registerProduct.request(request);

        assertThat(productRepository.findAll()).hasSize(1);
    }

}
