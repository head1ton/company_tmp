package ai.example.company_tmp.outbound.feature;

import ai.example.company_tmp.outbound.domain.MaterialType;
import ai.example.company_tmp.outbound.domain.PackagingMaterial;
import ai.example.company_tmp.outbound.domain.PackagingMaterialDimension;
import ai.example.company_tmp.outbound.domain.PackagingMaterialRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        @NotBlank(message = "포장재 이름은 필수입니다")
        String name,
        @NotBlank(message = "포장재 코드는 필수입니다.")
        String code,
        @NotNull(message = "내부 폭은 필수입니다.")
        long innerWidthInMillimeters,
        @NotNull(message = "내부 높이는 필수입니다.")
        long innerHeightInMillimeters,
        @NotNull(message = "내부 길이는 필수입니다.")
        long innerLengthInMillimeters,
        @NotNull(message = "외부 폭은 필수입니다.")
        long outerWidthInMillimeters,
        @NotNull(message = "외부 높이는 필수입니다.")
        long outerHeightInMillimeters,
        @NotNull(message = "외부 길이는 필수입니다.")
        long outerLengthInMillimeters,
        @NotNull(message = "무게는 필수입니다.")
        long weightInGrams,
        @NotNull(message = "최대 무게는 필수입니다.")
        long maxWeightInGrams,
        @NotNull(message = "포장재 종류는 필수입니다.")
        MaterialType materialType) {

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
