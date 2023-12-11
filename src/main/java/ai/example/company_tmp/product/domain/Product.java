package ai.example.company_tmp.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("상품")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_no")
    @Comment("상품 번호")
    private Long productNo;
    @Column(name = "name", nullable = false)
    @Comment("상품명")
    private String name;
    @Column(name = "code", nullable = false, unique = true)
    @Comment("상품코드")
    private String code;
    @Column(name = "description", nullable = false)
    @Comment("상품설명")
    private String description;
    @Column(name = "brand", nullable = false)
    @Comment("브랜드")
    private String brand;
    @Column(name = "maker", nullable = false)
    @Comment("제조사")
    private String maker;
    @Column(name = "origin", nullable = false)
    @Comment("원산지")
    private String origin;
    @Column(name = "category", nullable = false)
    @Comment("카테고리")
    private Category category;
    @Enumerated(EnumType.STRING)
    @Column(name = "temperature_zone", nullable = false)
    @Comment("온도대")
    private TemperatureZone temperatureZone;
    @Column(name = "weight_in_grams", nullable = false)
    @Comment("무게(그램)")
    private Long weightInGrams;
    @Embedded
    private ProductSize productSize;

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
        final String description,
        final String brand, final String maker, final String origin, final Category category,
        final TemperatureZone temperatureZone, final Long weightInGrams,
        final ProductSize productSize) {
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
}
