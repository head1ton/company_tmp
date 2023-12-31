package ai.example.company_tmp.location.domain;

import static ai.example.company_tmp.location.domain.InventoryFixture.anInventory;

import ai.example.company_tmp.outbound.domain.Inventories;
import java.util.Arrays;
import java.util.List;

public class InventoriesFixture {

    private List<InventoryFixture> inventories = List.of(anInventory());

    public static InventoriesFixture anInventories() {
        return new InventoriesFixture();
    }

    public Inventories build() {
        return new Inventories(buildInventories());
    }

    public InventoriesFixture inventories(final InventoryFixture... inventories) {
        this.inventories = Arrays.asList(inventories);
        return this;
    }

    private List<Inventory> buildInventories() {
        return inventories.stream()
                          .map(InventoryFixture::build)
                          .toList();
    }
}