package ai.example.company_tmp.outbound.feature;

import ai.example.company_tmp.outbound.domain.PackagingMaterial;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public record PackagingMaterials(List<PackagingMaterial> packagingMaterials) {

    Optional<PackagingMaterial> findOptimalPackagingMaterial(
        final Long totalWeight,
        final Long totalVolume) {
        return packagingMaterials().stream()
                                   .filter(
                                       pm -> pm.isAvailable(
                                           totalWeight,
                                           totalVolume))
                                   .min(
                                       Comparator.comparingLong(
                                           PackagingMaterial::outerVolume));
    }
}