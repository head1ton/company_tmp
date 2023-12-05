package ai.example.company_tmp.outbound.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

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

//        assertThat(registerPackingMaterialRepository.findAll()).hasSize(1);
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
            final PackingMaterial packingMaterial = request.toDomain();
        }

        public record Request(
            String name,
            String code,
            long innerWidthInMillimeters,
            long innerHeightInMillimeters,
            long innerLengthInMillimeters,
            long outerWidthInMillimeters,
            long outerHeightInMillimeters,
            long outerLengthInMillimeters,
            long weightInGrams,
            long maxWeightInGrams,
            MaterialType materialType) {

            public Request {
                Assert.hasText(name, "포장재 이름은 필수입니다.");
                Assert.hasText(code, "포장재 코드는 필수입니다.");
                Assert.notNull(innerWidthInMillimeters, "내부 폭은 필수입니다.");
                if (innerWidthInMillimeters < 1) {
                    throw new IllegalArgumentException("내부 폭은 1mm 이상이어야 합니다.");
                }
                Assert.notNull(innerHeightInMillimeters, "내부 높이는 필수입니다.");
                if (innerHeightInMillimeters < 1) {
                    throw new IllegalArgumentException("내부 높이는 1mm 이상이어야 합니다.");
                }
                Assert.notNull(innerLengthInMillimeters, "내부 길이는 필수입니다.");
                if (innerLengthInMillimeters < 1) {
                    throw new IllegalArgumentException("내부 길이는 1mm 이상이어야 합니다.");
                }
                Assert.notNull(outerWidthInMillimeters, "외부 폭은 필수입니다.");
                if (outerWidthInMillimeters < 1) {
                    throw new IllegalArgumentException("외부 폭은 1mm 이상이어야 합니다.");
                }
                Assert.notNull(outerHeightInMillimeters, "외부 높이는 필수입니다.");
                if (outerHeightInMillimeters < 1) {
                    throw new IllegalArgumentException("외부 높이는 1mm 이상이어야 합니다.");
                }
                Assert.notNull(outerLengthInMillimeters, "외부 길이는 필수입니다.");
                if (outerLengthInMillimeters < 1) {
                    throw new IllegalArgumentException("외부 길이는 1mm 이상이어야 합니다.");
                }
                Assert.notNull(weightInGrams, "무게는 필수입니다.");
                if (weightInGrams < 1) {
                    throw new IllegalArgumentException("무게는 1g 이상이어야 합니다.");
                }
                Assert.notNull(maxWeightInGrams, "최대 무게는 필수입니다.");
                if (maxWeightInGrams < 1) {
                    throw new IllegalArgumentException("최대 무게는 1g 이상이어야 합니다.");
                }
                Assert.notNull(materialType, "포장재 종류는 필수입니다.");
            }

            public PackingMaterial toDomain() {
                throw new UnsupportedOperationException("Unsupported toDomain");
            }
        }
    }

    public class PackingMaterial {

    }
}
