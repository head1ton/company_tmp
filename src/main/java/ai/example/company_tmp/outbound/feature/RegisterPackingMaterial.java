package ai.example.company_tmp.outbound.feature;

import ai.example.company_tmp.outbound.domain.MaterialType;
import ai.example.company_tmp.outbound.domain.PackagingMaterial;
import ai.example.company_tmp.outbound.domain.PackagingMaterialDimension;
import ai.example.company_tmp.outbound.domain.PackagingMaterialRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterPackingMaterial {

    private final PackagingMaterialRepository packagingMaterialRepository;

    @PostMapping("/packaging-materials")
    @ResponseStatus(HttpStatus.CREATED)
    public void request(@RequestBody @Valid final Request request) {
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
