package ai.example.company_tmp.location.domain;

import ai.example.company_tmp.inbound.domain.LPN;
import lombok.Getter;

public class LocationLPN {

    private final Location location;
    @Getter
    private final LPN lpn;
    @Getter
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

    public boolean matchLpnToLocation(final LPN lpn) {
        return this.lpn.equals(lpn);
    }
}
