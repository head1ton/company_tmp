package ai.example.company_tmp.location.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.inbound.domain.LPN;
import ai.example.company_tmp.inbound.domain.LPNFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocationTest {

    private static void assertAssignLPN(final Location location,
        final long expectedInventoryQuantity) {
        final List<Inventory> inventories = location.getInventories();
        final Inventory inventory = inventories.get(0);
        assertThat(inventories).hasSize(1);
        assertThat(inventory.getInventoryQuantity()).isEqualTo(expectedInventoryQuantity);
    }

    @Test
    @DisplayName("로케이션에 LPN을 할당한다.")
    void assignLPN() {
        final Location location = LocationFixture.aLocation().build();

        final LPN lpn = LPNFixture.anLPN().build();

        location.assignInventory(lpn);

        assertAssignLPN(location, 1L);
    }

    @Test
    @DisplayName("로케이션에 LPN을 할당한다. 이미 LPN이 존재하면 생성하지 않고 재고만 증가시킨다.")
    void already_exists_assignLPN() {
        final Location location = LocationFixture.aLocation().build();

        final LPN lpn = LPNFixture.anLPN().build();
        final LPN lpn2 = LPNFixture.anLPN().build();

        location.assignInventory(lpn);
        location.assignInventory(lpn2);

        assertAssignLPN(location, 2L);
    }

}