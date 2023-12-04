package ai.example.company_tmp.location.fixture;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.location.domain.LocationRepository;
import ai.example.company_tmp.location.domain.StorageType;
import ai.example.company_tmp.location.domain.UsagePurpose;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegisterLocationTest {

    private RegisterLocation registerLocation;
    private LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        locationRepository = new LocationRepository();
        registerLocation = new RegisterLocation(locationRepository);
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

        assertThat(locationRepository.findAll()).hasSize(1);
    }

}
