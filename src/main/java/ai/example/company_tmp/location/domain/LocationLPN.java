package ai.example.company_tmp.location.domain;

import ai.example.company_tmp.inbound.domain.LPN;

public class LocationLPN {

    private final Location location;
    private final LPN lpn;
    private Long inventoryQuantity;

    public LocationLPN(final Location location, final LPN lpn) {

        this.location = location;
        this.lpn = lpn;
        inventoryQuantity = 1L;
    }

    public void increaseQuantity() {
        inventoryQuantity++;
    }

    public LPN getLPN() {
        return lpn;
    }
}
