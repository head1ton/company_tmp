package ai.example.company_tmp.location.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.location.domain.Location;
import ai.example.company_tmp.location.domain.LocationLPN;
import ai.example.company_tmp.location.domain.LocationRepository;
import ai.example.company_tmp.location.feature.api.AssignLocationLPNApi;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

class AssignLocationLPNTest extends ApiTest {

    @Autowired
    private AssignLocationLPN assignLocationLPN;
    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach
    void setUpAssignLocationLPN() {
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
    void assignLocationLPN() {

        final String locationBarcode = AssignLocationLPNApi.assignLocationLPN();

//        assignLocationLPN.request(request);

        final Location location = locationRepository.getByLocationBarcode(locationBarcode);
        final List<LocationLPN> locationLPNList = location.getLocationLPNList();
        final LocationLPN locationLPN = locationLPNList.get(0);
        assertThat(locationLPNList).hasSize(1);
        assertThat(locationLPN.getInventoryQuantity()).isEqualTo(1L);
    }

}
