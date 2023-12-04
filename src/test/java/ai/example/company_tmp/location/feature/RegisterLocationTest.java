package ai.example.company_tmp.location.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.common.ApiTest;
import ai.example.company_tmp.common.Scenario;
import ai.example.company_tmp.location.domain.LocationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RegisterLocationTest extends ApiTest {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    @DisplayName("로케이션을 등록한다.")
    void registerLocation() {
        Scenario
            .registerLocation()
//            .locationBarcode(null)
            .request();

//        registerLocation.request(request);

        assertThat(locationRepository.findAll()).hasSize(1);
    }

}
