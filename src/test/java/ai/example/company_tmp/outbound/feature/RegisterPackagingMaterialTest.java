package ai.example.company_tmp.outbound.feature;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

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

    public enum MaterialType {
        CORRUGATED_BOX("완충재가 있는 골판지 상자"),
        ;

        private final String description;

        MaterialType(final String description) {
            this.description = description;
        }
    }

    public static class PackagingMaterialDimension {

        private final long innerWidthInMillimeters;
        private final long innerHeightInMillimeters;
        private final long innerLengthInMillimeters;
        private final long outerWidthInMillimeters;
        private final long outerHeightInMillimeters;
        private final long outerLengthInMillimeters;

        public PackagingMaterialDimension(final long innerWidthInMillimeters,
            final long innerHeightInMillimeters, final long innerLengthInMillimeters,
            final long outerWidthInMillimeters, final long outerHeightInMillimeters,
            final long outerLengthInMillimeters) {
            validateConstructor(innerWidthInMillimeters, innerHeightInMillimeters,
                innerLengthInMillimeters,
                outerWidthInMillimeters, outerHeightInMillimeters,
                outerLengthInMillimeters);
            this.innerWidthInMillimeters = innerWidthInMillimeters;
            this.innerHeightInMillimeters = innerHeightInMillimeters;
            this.innerLengthInMillimeters = innerLengthInMillimeters;
            this.outerWidthInMillimeters = outerWidthInMillimeters;
            this.outerHeightInMillimeters = outerHeightInMillimeters;
            this.outerLengthInMillimeters = outerLengthInMillimeters;
        }

        private void validateConstructor(final long innerWidthInMillimeters,
            final long innerHeightInMillimeters, final long innerLengthInMillimeters,
            final long outerWidthInMillimeters, final long outerHeightInMillimeters,
            final long outerLengthInMillimeters) {
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
        }
    }

    public static class PackagingMaterial {

        @Getter
        private Long packagingMaterialNo;
        private final String name;
        private final String code;
        private final PackagingMaterialDimension packagingMaterialDimension;
        private final long weightInGrams;
        private final long maxWeightInGrams;
        private final MaterialType materialType;

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

    public class RegisterPackingMaterial {

        private final PackagingMaterialRepository packagingMaterialRepository;

        public RegisterPackingMaterial(
            final PackagingMaterialRepository packagingMaterialRepository) {
            this.packagingMaterialRepository = packagingMaterialRepository;
        }

        public void request(final Request request) {
            final PackagingMaterial packagingMaterial = request.toDomain();

            packagingMaterialRepository.save(packagingMaterial);
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

            public PackagingMaterial toDomain() {
                return new PackagingMaterial(
                    name,
                    code,
                    new PackagingMaterialDimension(
                        innerWidthInMillimeters,
                        innerHeightInMillimeters,
                        innerLengthInMillimeters,
                        outerWidthInMillimeters,
                        outerHeightInMillimeters,
                        outerLengthInMillimeters
                    ),
                    weightInGrams,
                    maxWeightInGrams,
                    materialType
                );
            }
        }
    }

    public class PackagingMaterialRepository {

        private final Map<Long, PackagingMaterial> packagingMaterialMap = new HashMap<>();
        private Long sequence = 1L;
        public void save(final PackagingMaterial packagingMaterial) {
            packagingMaterial.assignNo(sequence++);
            packagingMaterialMap.put(packagingMaterial.getPackagingMaterialNo(), packagingMaterial);
        }

        public List<PackagingMaterial> findAll() {
            return new ArrayList<>(packagingMaterialMap.values());
        }
    }
}
