package ai.example.company_tmp.outbound.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.outbound.domain.MaterialType;
import ai.example.company_tmp.outbound.domain.PackagingMaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegisterPackagingMaterialTest {

    private RegisterPackingMaterial registerPackingMaterial;
    private PackagingMaterialRepository packagingMaterialRepository;

    @BeforeEach
    void setUp() {
        packagingMaterialRepository = new PackagingMaterialRepository();
        registerPackingMaterial = new RegisterPackingMaterial(packagingMaterialRepository);
    }

    @Test
    @DisplayName("포장재를 등록한다.")
    void registerPackingMaterial() {
        final String name = "name";
        final String code = "code";
        final long innerWidthInMillimeters = 1000L;
        final long innerHeightInMillimeters = 1000L;
        final long innerLengthInMillimeters = 1000L;
        final long outerWidthInMillimeters = 1000L;
        final long outerHeightInMillimeters = 1000L;
        final long outerLengthInMillimeters = 1000L;
        final long weightInGrams = 100L;
        final long maxWeightInGrams = 100L;
        final MaterialType materialType = MaterialType.CORRUGATED_BOX;
        final RegisterPackingMaterial.Request request = new RegisterPackingMaterial.Request(
            name,
            code,
            innerWidthInMillimeters,
            innerHeightInMillimeters,
            innerLengthInMillimeters,
            outerWidthInMillimeters,
            outerHeightInMillimeters,
            outerLengthInMillimeters,
            weightInGrams,
            maxWeightInGrams,
            materialType
        );

        registerPackingMaterial.request(request);

        assertThat(packagingMaterialRepository.findAll()).hasSize(1);
    }

}