package ai.example.company_tmp.outbound.feature;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import ai.example.company_tmp.inbound.domain.LPN;
import ai.example.company_tmp.inbound.domain.LPNFixture;
import ai.example.company_tmp.location.domain.Inventory;
import ai.example.company_tmp.location.domain.Location;
import ai.example.company_tmp.location.domain.LocationFixture;
import ai.example.company_tmp.outbound.domain.Order;
import ai.example.company_tmp.outbound.domain.OrderFixture;
import ai.example.company_tmp.outbound.domain.OrderProductFixture;
import ai.example.company_tmp.outbound.domain.Outbound;
import ai.example.company_tmp.outbound.domain.PackagingMaterial;
import ai.example.company_tmp.outbound.domain.PackagingMaterialFixture;
import ai.example.company_tmp.outbound.domain.PackagingMaterials;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class RegisterOutboundUnitTest {
    private RegisterOutbound registerOutbound;

    @BeforeEach
    void setUp() {
        registerOutbound = new RegisterOutbound(null, null, null, null);
    }

    @Test
    @DisplayName("출고")
    void createOutbound() {
        final Inventory inventory = new Inventory(
            LocationFixture.aLocation().build(),
            LPNFixture.anLPN().build()
        );
        final Order order = OrderFixture.anOrder().build();
        final Inventories inventories = new Inventories(List.of(inventory), 1L);
        final PackagingMaterial packagingMaterial = PackagingMaterialFixture.aPackagingMaterial()
                                                                            .build();
        final PackagingMaterials packagingMaterials = new PackagingMaterials(
            List.of(packagingMaterial));
        final List<Inventories> inventoriesList = List.of(inventories);

        final Outbound outbound = registerOutbound.createOutbound(inventoriesList,
            packagingMaterials,
            order, false, LocalDate.now());

        assertThat(outbound).isNotNull();
    }

    @Test
    @DisplayName("출고 - 실패(수량)")
    void fail_over_quantity_createOutbound() {
        final Inventory inventory = new Inventory(
            LocationFixture.aLocation().build(),
            LPNFixture.anLPN().build()
        );
        final Order order = OrderFixture.anOrder().build();
        final Inventories inventories = new Inventories(List.of(inventory), 2L);
        final PackagingMaterial packagingMaterial = PackagingMaterialFixture.aPackagingMaterial()
                                                                            .build();
        final PackagingMaterials packagingMaterials = new PackagingMaterials(
            List.of(packagingMaterial));
        final List<Inventories> inventoriesList = List.of(inventories);

        assertThatThrownBy(() -> {
            registerOutbound.createOutbound(inventoriesList, packagingMaterials,
                order, false, LocalDate.now());
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("재고가 부족합니다.");
    }

    @Test
    @DisplayName("출고 - 실패(유통기한)")
    void expire_createOutbound() {
        final Inventory inventory = new Inventory(
            LocationFixture.aLocation().build(),
            LPNFixture.anLPN().expirationAt(LocalDateTime.now().minusDays(1)).build()
        );
        final Order order = OrderFixture.anOrder().build();
        final Inventories inventories = new Inventories(List.of(inventory), 1L);
        final PackagingMaterial packagingMaterial = PackagingMaterialFixture.aPackagingMaterial()
                                                                            .build();
        final PackagingMaterials packagingMaterials = new PackagingMaterials(
            List.of(packagingMaterial));
        final List<Inventories> inventoriesList = List.of(inventories);

        assertThatThrownBy(() -> {
            registerOutbound.createOutbound(inventoriesList, packagingMaterials,
                order, false, LocalDate.now());
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("재고가 부족합니다.");
    }

    @Test
    @DisplayName("출고 - 실패(무게)")
    void over_max_weight_createOutbound() {
        final Inventory inventory = new Inventory(
            LocationFixture.aLocation().build(),
            LPNFixture.anLPN().build()
        );
        final Order order = OrderFixture.anOrder().build();
        final Inventories inventories = new Inventories(List.of(inventory), 1L);
        final PackagingMaterial packagingMaterial = PackagingMaterialFixture.aPackagingMaterial()
                                                                            .maxWeightInGrams(1L)
                                                                            .build();
        final PackagingMaterials packagingMaterials = new PackagingMaterials(
            List.of(packagingMaterial));
        final List<Inventories> inventoriesList = List.of(inventories);

        final Outbound outbound = registerOutbound.createOutbound(inventoriesList,
            packagingMaterials,
            order, false, LocalDate.now());

        assertThat(outbound.getRecommendedPackagingMaterial()).isNull();
    }

    @Test
    @DisplayName("출고 - 실패(부피)")
    void over_volume_createOutbound() {
        final Inventory inventory = new Inventory(
            LocationFixture.aLocation().build(),
            LPNFixture.anLPN().build()
        );
        final Order order = OrderFixture.anOrder().build();
        final Inventories inventories = new Inventories(List.of(inventory), 1L);
        final PackagingMaterial packagingMaterial = PackagingMaterialFixture.aPackagingMaterial()
                                                                            .packagingMaterialDimension(
                                                                                PackagingMaterialDimensionFixture.aPackagingMaterialDimension()
                                                                                                                 .innerHeightInMillimeters(
                                                                                                                     1L)
                                                                                                                 .innerWidthInMillimeters(
                                                                                                                     1L))
                                                                            .build();
        final PackagingMaterials packagingMaterials = new PackagingMaterials(
            List.of(packagingMaterial));
        final List<Inventories> inventoriesList = List.of(inventories);

        final Outbound outbound = registerOutbound.createOutbound(inventoriesList,
            packagingMaterials,
            order, false, LocalDate.now());

        assertThat(outbound.getRecommendedPackagingMaterial()).isNull();
    }

    @Test
    @DisplayName("주문한 상품을 포장할 수 있는 포장재를 찾는다")
    void findOptimalPackagingMaterial() {
        final Order order = OrderFixture.anOrder().build();
        final PackagingMaterial packagingMaterial = PackagingMaterialFixture.aPackagingMaterial()
                                                                            .build();

        final Optional<PackagingMaterial> optimalPackagingMaterial = new PackagingMaterials(
            List.of(packagingMaterial)).findOptimalPackagingMaterial(
            order.totalWeight(), order.totalVolume());

        assertThat(optimalPackagingMaterial.isPresent()).isEqualTo(true);
    }

    @Test
    @DisplayName("주문한 상품을 포장할 수 있는 포장재를 찾는다 - 실패")
    void empty_findOptimalPackagingMaterial() {
        final Order order = OrderFixture.anOrder().orderProduct(
            OrderProductFixture.anOrderProduct().orderQuantity(100L)).build();
        final PackagingMaterial packagingMaterial = PackagingMaterialFixture.aPackagingMaterial()
                                                                            .build();

        final Optional<PackagingMaterial> optimalPackagingMaterial = new PackagingMaterials(
            List.of(packagingMaterial)).findOptimalPackagingMaterial(
            order.totalWeight(), order.totalVolume());

        assertThat(optimalPackagingMaterial.isPresent()).isEqualTo(false);
    }

    @Test
    @DisplayName("주문한 상품을 출고할 수 있는 재고가 있는지 확인한다.")
    void validateInventory() {
        final Inventory inventory = new Inventory(
            LocationFixture.aLocation().build(),
            LPNFixture.anLPN().build()
        );
        new Inventories(List.of(inventory), 1L).validateInventory();
    }

    @Test
    @DisplayName("주문한 상품이 출고할 수 있는 재고보다 많다면")
    void fail_over_quantity_validateInventory() {
        final Inventory inventory = new Inventory(
            LocationFixture.aLocation().build(),
            LPNFixture.anLPN().build()
        );

        assertThatThrownBy(() -> {
            new Inventories(List.of(inventory), 2L).validateInventory();
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("재고가 부족합니다.");
    }

    @Test
    @DisplayName("유통기한이 지났으면 재고가 있어도")
    void expire_validateInventory() {
        final Location location = LocationFixture.aLocation().build();
        final LPN lpn = LPNFixture.anLPN().expirationAt(LocalDateTime.now().minusDays(1)).build();
        final Inventory inventory = new Inventory(
            location,
            lpn
        );

        assertThatThrownBy(() -> {
            new Inventories(List.of(inventory), 1L).validateInventory();
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("재고가 부족합니다.");
    }
}
