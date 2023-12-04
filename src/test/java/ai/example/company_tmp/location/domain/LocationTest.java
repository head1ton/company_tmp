package ai.example.company_tmp.location.domain;

import ai.example.company_tmp.inbound.domain.InboundItemFixture;
import ai.example.company_tmp.inbound.domain.LPN;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocationTest {

    @Test
    @DisplayName("로케이션에 LPN을 할당한다.")
    void assignLPN() {
        final Location location = createLocation();

        final LPN lpn = createLPN();

        location.assignLPN(lpn);


    }

    private LPN createLPN() {
        final String lpnBarcode = "LPN-1";
        final LocalDateTime expirationAt = LocalDateTime.now().plusDays(1);
        return new LPN(lpnBarcode, expirationAt,
            InboundItemFixture.anInboundItem().build());
    }

    private Location createLocation() {
        final String locationBarcode = "A-1-1";
        final StorageType storageType = StorageType.TOTE;
        final UsagePurpose usagePurpose = UsagePurpose.MOVE;
        return new Location(locationBarcode, storageType, usagePurpose);
    }
}