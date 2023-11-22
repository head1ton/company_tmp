package ai.example.company_tmp.product.domain;

import org.springframework.util.Assert;

public class Product {

    private Long id;
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

    public Product(final String name, final String code, final String description,
        final String brand, final String maker,
        final String origin, final Category category, final TemperatureZone temperatureZone,
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

    public void assignId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
