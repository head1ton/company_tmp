package ai.example.company_tmp.outbound.feature;

import ai.example.company_tmp.outbound.domain.PackagingMaterial;
import ai.example.company_tmp.outbound.domain.PackagingMaterialFixture;
import java.util.Arrays;
import java.util.List;

public class PackagingMaterialsFixture {

    private List<PackagingMaterialFixture> packagingMaterials = List.of(
        PackagingMaterialFixture.aPackagingMaterial());

    public static PackagingMaterialsFixture aPackagingMaterials() {
        return new PackagingMaterialsFixture();
    }

    public PackagingMaterialsFixture packagingMaterials(
        final PackagingMaterialFixture... packagingMaterialFixtures) {
        this.packagingMaterials = Arrays.asList(packagingMaterialFixtures);
        return this;
    }

    PackagingMaterials build() {
        return new PackagingMaterials(buildPackagingMaterials());
    }

    private List<PackagingMaterial> buildPackagingMaterials() {
        return packagingMaterials.stream()
                                 .map(PackagingMaterialFixture::build)
                                 .toList();
    }

}
