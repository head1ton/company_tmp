package ai.example.company_tmp.location.fixture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegisterLocationTest {

    private RegisterLocation registerLocation;

    @BeforeEach
    void setUp() {
        registerLocation = new RegisterLocation();
    }

    @Test
    @DisplayName("로케이션을 등록한다.")
    void registerLocation() {
        final String locationBarcode = "A-1-1";

        final StorageType storageType = StorageType.TOTE;
        final UsagePurpose usagePurpose = UsagePurpose.MOVE;
        final RegisterLocation.Request request = new RegisterLocation.Request(
            locationBarcode,
            storageType,
            usagePurpose
        );
        registerLocation.request(request);
    }

    public enum StorageType {
        TOTE("토트바구니"),
        ;

        private final String description;

        StorageType(final String description) {
            this.description = description;
        }
    }

    public enum UsagePurpose {
        MOVE("이동 목적"),
        ;

        private final String description;

        UsagePurpose(final String description) {
            this.description = description;
        }
    }

    public static class Location {

        private final String locationBarcode;
        private final StorageType storageType;
        private final UsagePurpose usagePurpose;

        public Location(
            final String locationBarcode,
            final StorageType storageType,
            final UsagePurpose usagePurpose) {
            this.locationBarcode = locationBarcode;
            this.storageType = storageType;
            this.usagePurpose = usagePurpose;
        }
    }

    public class RegisterLocation {

        public void request(final Request request) {
            final Location location = request.toDomain();
        }

        public record Request(
            String locationBarcode,
            StorageType storageType,
            UsagePurpose usagePurpose) {

            public Location toDomain() {
                return new Location(locationBarcode, storageType, usagePurpose);
            }
        }
    }
}
