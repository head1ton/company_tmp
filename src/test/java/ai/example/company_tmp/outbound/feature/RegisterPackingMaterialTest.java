package ai.example.company_tmp.outbound.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegisterPackingMaterialTest {

    private RegisterPackingMaterial registerPackingMaterial;

    @BeforeEach
    void setUp() {
        registerPackingMaterial = new RegisterPackingMaterial();
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
            MaterialType.CORRUGATED_BOX
        );
        registerPackingMaterial.request(request);
    }

    public enum MaterialType {
        CORRUGATED_BOX("완충재가 있는 골판지 상자"),
        ;

        private final String description;

        MaterialType(final String description) {
            this.description = description;
        }
    }

    public class RegisterPackingMaterial {

        public void request(final Request request) {
            throw new UnsupportedOperationException("Unsupported request");
        }

        public record Request(String name, String code, long innerWidthInMillimeters,
                              long innerHeightInMillimeters, long innerLengthInMillimeters,
                              long outerWidthInMillimeters, long outerHeightInMillimeters,
                              long outerLengthInMillimeters, long weightInGrams,
                              long maxWeightInGrams, MaterialType corrugatedBox) {

        }
    }
}
