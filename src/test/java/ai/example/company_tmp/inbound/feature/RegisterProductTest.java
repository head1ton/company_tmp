package ai.example.company_tmp.inbound.feature;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

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

    public enum Category {
        ELECTRONICS("전자 제품");

        private final String description;

        Category(final String description) {
            this.description = description;
        }
    }

    public enum TemperatureZone {
        ROOM_TEMPERATURE("상온");

        private final String description;

        TemperatureZone(final String description) {
            this.description = description;
        }
    }

    public static class ProductSize {

        private final Long widthInMillimeters;
        private final Long heightInMillimeters;
        private final Long lengthInMillimeters;

        public ProductSize(final Long widthInMillimeters, final Long heightInMillimeters,
            final Long lengthInMillimeters) {

            validateConstructor(widthInMillimeters, heightInMillimeters, lengthInMillimeters);
            this.widthInMillimeters = widthInMillimeters;
            this.heightInMillimeters = heightInMillimeters;
            this.lengthInMillimeters = lengthInMillimeters;
        }

        private static void validateConstructor(final Long widthInMillimeters,
            final Long heightInMillimeters, final Long lengthInMillimeters) {
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
    }

    private static class Product {

        private final String name;
        private final String code;
        private final String description;
        private final String brand;
        private final String maker;
        private final String origin;
        private final Category category;
        private final TemperatureZone temperatureZone;
        private final Long weightInGrams;
        private final ProductSize productSize;
        private Long id;

        public Product(final String name, final String code, final String description,
            final String brand, final String maker,
            final String origin, final Category category, final TemperatureZone temperatureZone,
            final Long weightInGrams, final ProductSize productSize) {

            validateConstructor(name, code, description, brand, maker, origin, category,
                temperatureZone,
                weightInGrams,
                productSize);

            this.name = name;
            this.code = code;
            this.description = description;
            this.brand = brand;
            this.maker = maker;
            this.origin = origin;
            this.category = category;
            this.temperatureZone = temperatureZone;
            this.weightInGrams = weightInGrams;
            this.productSize = productSize;
        }

        private static void validateConstructor(final String name, final String code,
            final String description, final String brand, final String maker, final String origin,
            final Category category, final TemperatureZone temperatureZone,
            final Long weightInGrams, final ProductSize productSize) {
            Assert.hasText(name, "name 필수입니다.");
            Assert.hasText(code, "code 필수입니다.");
            Assert.hasText(description, "description 필수입니다.");
            Assert.hasText(brand, "brand 필수입니다.");
            Assert.hasText(maker, "maker 필수입니다.");
            Assert.hasText(origin, "origin 필수입니다.");
            Assert.notNull(category, "category 필수입니다.");
            Assert.notNull(temperatureZone, "temperature zone 필수입니다.");
            Assert.notNull(weightInGrams, "weightInGrams 필수입니다.");
            Assert.notNull(productSize, "상품 크기는 필수입니다.");
        }

        public void assignId(final Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

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

    public class ProductRepository {

        private final Map<Long, Product> products = new HashMap<>();
        private Long sequence = 1L;

        public void save(final Product product) {
            product.assignId(sequence++);
            products.put(product.getId(), product);
        }

        public List<Product> findAll() {
            return new ArrayList<>(products.values());
        }
    }
}
