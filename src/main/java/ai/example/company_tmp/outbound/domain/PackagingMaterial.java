package ai.example.company_tmp.outbound.domain;

import lombok.Getter;
import org.springframework.util.Assert;

public class PackagingMaterial {

    private final String name;
    private final String code;
    private final PackagingMaterialDimension packagingMaterialDimension;
    private final long weightInGrams;
    private final long maxWeightInGrams;
    private final MaterialType materialType;
    @Getter
    private Long packagingMaterialNo;

    public PackagingMaterial(
        final String name,
        final String code,
        final PackagingMaterialDimension packagingMaterialDimension,
        final long weightInGrams,
        final long maxWeightInGrams,
        final MaterialType materialType) {
        validateConstructor(name, code, packagingMaterialDimension, weightInGrams,
            maxWeightInGrams, materialType);
        this.name = name;
        this.code = code;
        this.packagingMaterialDimension = packagingMaterialDimension;
        this.weightInGrams = weightInGrams;
        this.maxWeightInGrams = maxWeightInGrams;
        this.materialType = materialType;
    }

    private static void validateConstructor(final String name, final String code,
        final PackagingMaterialDimension packagingMaterialDimension, final long weightInGrams,
        final long maxWeightInGrams, final MaterialType materialType) {
        Assert.hasText(name, "포장재 이름은 필수입니다.");
        Assert.hasText(code, "포장재 코드는 필수입니다.");
        Assert.notNull(packagingMaterialDimension, "포장재 치수는 필수입니다.");
        Assert.notNull(weightInGrams, "무게는 필수입니다.");
        Assert.notNull(maxWeightInGrams, "최대 무게는 필수입니다.");
        Assert.notNull(materialType, "포장재 종류는 필수입니다.");
    }

    public void assignNo(final Long packagingMaterialNo) {
        this.packagingMaterialNo = packagingMaterialNo;
    }
}
