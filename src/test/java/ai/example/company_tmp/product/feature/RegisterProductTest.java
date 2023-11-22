package ai.example.company_tmp.product.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.product.common.Scenario;
import ai.example.company_tmp.product.domain.ProductRepository;
import ai.example.company_tmp.product.feature.api.RegisterProductApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RegisterProductTest extends ApiTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("상품 등록")
    void registerProduct() {
        Scenario.registerProduct().request()
                .registerProduct().code("code1").request();

        assertThat(productRepository.findAll()).hasSize(2);
    }

}
