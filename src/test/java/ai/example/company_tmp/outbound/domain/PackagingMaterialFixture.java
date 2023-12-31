package ai.example.company_tmp.outbound.domain;

import ai.example.company_tmp.outbound.feature.PackagingMaterialDimensionFixture;

public class PackagingMaterialFixture {

    private String name = "name";
    private String code = "code";
    private PackagingMaterialDimensionFixture dimension = PackagingMaterialDimensionFixture.aPackagingMaterialDimension();
    private Long weightInGrams = 100L;
    private Long maxWeightInGrams = 1000L;
    private MaterialType materialType = MaterialType.CORRUGATED_BOX;

    public static PackagingMaterialFixture aPackagingMaterial() {
        return new PackagingMaterialFixture();
    }

    public PackagingMaterialFixture name(final String name) {
        this.name = name;
        return this;
    }

    public PackagingMaterialFixture code(final String code) {
        this.code = code;
        return this;
    }

    public PackagingMaterialFixture dimension(
        final PackagingMaterialDimensionFixture packagingMaterialDimensionFixture) {
        this.dimension = packagingMaterialDimensionFixture;
        return this;
    }

    public PackagingMaterialFixture weightInGrams(final Long weightInGrams) {
        this.weightInGrams = weightInGrams;
        return this;
    }

    public PackagingMaterialFixture maxWeightInGrams(final Long maxWeightInGrams) {
        this.maxWeightInGrams = maxWeightInGrams;
        return this;
    }

    public PackagingMaterialFixture materialType(final MaterialType materialType) {
        this.materialType = materialType;
        return this;
    }

    public PackagingMaterial build() {

        return new PackagingMaterial(
            name,
            code,
            dimension.build(),
            weightInGrams,
            maxWeightInGrams,
            materialType
        );
    }
}