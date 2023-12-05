package ai.example.company_tmp.location.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.location.domain.Inventory;
import ai.example.company_tmp.location.domain.Location;
import ai.example.company_tmp.location.domain.LocationRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

class AssignInventoryTest extends ApiTest {

    @Autowired
    private AssignInventory assignInventory;
    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach
    void setUpAssignInventory() {
        Scenario
            .registerProduct().request()
            .registerInbound().request()
            .confirmInbound().request()
            .registerLPN().request()
            .registerLocation().request();

    }

    @Test
    @DisplayName("로케이션에 LPN을 할당한다.")
    @Transactional
    void assignInventory() {

        Scenario.assignInventory().build();

        assertAssignInventory();
    }

    private void assertAssignInventory() {
        final Location location = locationRepository.getByLocationBarcode("A-1-1");
        final List<Inventory> inventories = location.getInventories();
        final Inventory inventory = inventories.get(0);
        assertThat(inventories).hasSize(1);
        assertThat(inventory.getInventoryQuantity()).isEqualTo(1L);
    }

}
