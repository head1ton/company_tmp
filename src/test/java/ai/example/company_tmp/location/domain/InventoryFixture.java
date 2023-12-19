package ai.example.company_tmp.location.domain;

import static ai.example.company_tmp.inbound.domain.LPNFixture.anLPN;
import static ai.example.company_tmp.location.domain.LocationFixture.aLocation;

import ai.example.company_tmp.inbound.domain.LPNFixture;

public class InventoryFixture {

    private LocationFixture location = aLocation();
    private LPNFixture lpn = anLPN();

    public static InventoryFixture anInventory() {
        return new InventoryFixture();
    }

    public InventoryFixture location(final LocationFixture location) {
        this.location = location;
        return this;
    }

    public InventoryFixture lpn(final LPNFixture lpn) {
        this.lpn = lpn;
        return this;
    }

    public Inventory build() {
        return new Inventory(location.build(), lpn.build());
    }
}
