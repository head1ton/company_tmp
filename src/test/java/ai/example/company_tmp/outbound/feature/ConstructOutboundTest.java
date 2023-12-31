package ai.example.company_tmp.outbound.feature;

import static ai.example.company_tmp.inbound.domain.LPNFixture.anLPN;
import static ai.example.company_tmp.location.domain.InventoriesFixture.anInventories;
import static ai.example.company_tmp.location.domain.InventoryFixture.anInventory;
import static ai.example.company_tmp.outbound.domain.OrderFixture.anOrder;
import static ai.example.company_tmp.outbound.domain.OrderProductFixture.anOrderProduct;
import static ai.example.company_tmp.outbound.domain.PackagingMaterialFixture.aPackagingMaterial;
import static ai.example.company_tmp.outbound.feature.PackagingMaterialDimensionFixture.aPackagingMaterialDimension;
import static ai.example.company_tmp.outbound.feature.PackagingMaterialsFixture.aPackagingMaterials;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import ai.example.company_tmp.outbound.domain.Outbound;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ConstructOutboundTest {

    ConstructOutbound sut = new ConstructOutbound();

    @Test
    @DisplayName("출고를 생성한다.")
    void createOutbound() {
        final Outbound outbound = sut.create(
            List.of(anInventories().build()),
            aPackagingMaterials().build(),
            anOrder().build(),
            false,
            LocalDate.now());
        assertThat(outbound).isNotNull();
    }
    @Test
    @DisplayName("출고를 생성한다. - 출고 수량이 재고 수량보다 많을 경우 예외가 발생한다.")
    void fail_over_quantity_createOutbound() {

        assertThatThrownBy(() -> {
            sut.create(
                List.of(anInventories().build()),
                aPackagingMaterials().build(),
                anOrder().orderProduct(
                    anOrderProduct().orderQuantity(2L)
                ).build(),
                false,
                LocalDate.now());
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("재고가 부족합니다.");
    }
    @Test
    @DisplayName("출고를 생성한다. - (유통기한이 지나서 재고가 부족)출고 수량이 재고 수량보다 많을 경우 예외가 발생한다.")
    void expire_createOutbound() {

        assertThatThrownBy(() -> {
            sut.create(
                List.of(anInventories()
                    .inventories(anInventory()
                        .lpn(anLPN().expirationAt(LocalDateTime.now().minusDays(1))))
                    .build()),
                aPackagingMaterials().build(),
                anOrder().build(),
                false,
                LocalDate.now());
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("재고가 부족합니다.");
    }
    @Test
    @DisplayName("출고를 생선한다. - 주문을 포장할 포장재를 찾을 수 없다. - 제한 무게를 초과")
    void over_max_weight_createOutbound() {
        final Outbound outbound = sut.create(
            List.of(anInventories().build()),
            aPackagingMaterials().packagingMaterials(
                aPackagingMaterial().maxWeightInGrams(1L)
            ).build(),
            anOrder().build(),
            false,
            LocalDate.now());
        assertThat(outbound.getRecommendedPackagingMaterial()).isNull();
    }
    @Test
    @DisplayName("출고를 생선한다. - 주문을 포장할 포장재를 찾을 수 없다. - 허용 가능한 부피 초과")
    void over_volume_createOutbound() {
        final Outbound outbound = sut.create(
            List.of(anInventories().build()),
            aPackagingMaterials().packagingMaterials(
                aPackagingMaterial().dimension(
                    aPackagingMaterialDimension()
                        .innerHeightInMillimeters(1L)
                        .innerWidthInMillimeters(1L)
                )
            ).build(),
            anOrder().build(),
            false,
            LocalDate.now());
        assertThat(outbound.getRecommendedPackagingMaterial()).isNull();
    }
}
