package ai.example.company_tmp.product.feature;

import ai.example.company_tmp.product.domain.Category;
import ai.example.company_tmp.product.domain.Product;
import ai.example.company_tmp.product.domain.ProductRepository;
import ai.example.company_tmp.product.domain.ProductSize;
import ai.example.company_tmp.product.domain.TemperatureZone;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterProduct {

    private final ProductRepository productRepository;

    public RegisterProduct(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void request(@RequestBody final Request request) {
        System.out.println("=============== = " + request);

        validateProductCodeExists(request.code);

        Product product = request.toDomain();
        productRepository.save(product);
    }

    private void validateProductCodeExists(final String code) {
        productRepository.findAll().stream()
                         .filter(product -> product.getCode().equals(code))
                         .findFirst()
                         .ifPresent(product -> {
                             throw new IllegalArgumentException(
                                 "이미 등록된 상품 코드입니다. %s".formatted(code));
                         });
    }

    public record Request(
        @NotBlank(message = "상품명은 필수입니다.")
        String name,
        @NotBlank(message = "상품코드는 필수입니다.")
        String code,
        @NotBlank(message = "상품설명은 필수입니다.")
        String description,
        @NotBlank(message = "브랜드는 필수입니다.")
        String brand,
        @NotBlank(message = "제조사는 필수입니다.")
        String maker,
        @NotBlank(message = "원산지는 필수입니다.")
        String origin,
        @NotNull(message = "카테고리는 필수입니다.")
        Category category,
        @NotNull(message = "온도대는 필수입니다.")
        TemperatureZone temperatureZone,
        @NotNull(message = "무게는 필수입니다.")
        @Min(value = 0, message = "무게는 0보다 작을 수 없습니다.")
        Long weightInGrams,
        @NotNull(message = "상품의 너비는 필수입니다.")
        @Min(value = 0, message = "상품의 너비는 0보다 작을 수 없습니다.")
        Long widthInMillimeters,
        @NotNull(message = "상품의 높이는 필수입니다.")
        @Min(value = 0, message = "상품의 높이는 0보다 작을 수 없습니다.")
        Long heightInMillimeters,
        @NotNull(message = "상품의 길이는 필수입니다.")
        @Min(value = 0, message = "상품의 길이는 0보다 작을 수 없습니다.")
        Long lengthInMillimeters) {

        public Product toDomain() {
            return new Product(
                name,
                code,
                description,
                brand,
                maker,
                origin,
                category,
                temperatureZone,
                weightInGrams,
                new ProductSize(widthInMillimeters, heightInMillimeters,
                    lengthInMillimeters)
            );
        }
    }

}
