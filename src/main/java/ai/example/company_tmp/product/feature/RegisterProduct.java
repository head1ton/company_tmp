package ai.example.company_tmp.product.feature;

import ai.example.company_tmp.product.domain.Category;
import ai.example.company_tmp.product.domain.Product;
import ai.example.company_tmp.product.domain.ProductRepository;
import ai.example.company_tmp.product.domain.ProductSize;
import ai.example.company_tmp.product.domain.TemperatureZone;
import org.springframework.util.Assert;

public class RegisterProduct {

    private final ProductRepository productRepository;

    public RegisterProduct(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void request(final Request request) {
        Product product = request.toDomain();
        productRepository.save(product);
    }

    public record Request(String name, String code, String description, String brand,
                          String maker, String origin, Category category,
                          TemperatureZone temperatureZone, Long weightInGrams,
                          Long widthInMillimeters, Long heightInMillimeters,
                          Long lengthInMillimeters) {

        public Request {
            Assert.hasText(name, "name 필수입니다.");
            Assert.hasText(code, "code 필수입니다.");
            Assert.hasText(description, "description 필수입니다.");
            Assert.hasText(brand, "brand 필수입니다.");
            Assert.hasText(maker, "maker 필수입니다.");
            Assert.hasText(origin, "origin 필수입니다.");
            Assert.notNull(category, "category 필수입니다.");
            Assert.notNull(temperatureZone, "temperature zone 필수입니다.");
            Assert.notNull(weightInGrams, "weightInGrams 필수입니다.");
            if (0 > weightInGrams) {
                throw new IllegalArgumentException("무게는 0보다 작을 수 없습니다.");
            }
            Assert.notNull(widthInMillimeters, "가로 길이는 필수입니다.");
            if (0 > widthInMillimeters) {
                throw new IllegalArgumentException("가로 길이는 0보다 작을 수 없습니다.");
            }
            Assert.notNull(heightInMillimeters, "세로 길이는 필수입니다.");
            if (0 > heightInMillimeters) {
                throw new IllegalArgumentException("세로 길이는 0보다 작을 수 없습니다.");
            }
            Assert.notNull(lengthInMillimeters, "길이는 필수입니다.");
            if (0 > lengthInMillimeters) {
                throw new IllegalArgumentException("길이는 0보다 작을 수 없습니다.");
            }
        }

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
                new ProductSize(widthInMillimeters, heightInMillimeters, lengthInMillimeters)
            );
        }
    }
}
