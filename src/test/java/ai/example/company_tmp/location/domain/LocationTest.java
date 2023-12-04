package ai.example.company_tmp.location.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.inbound.domain.LPN;
import ai.example.company_tmp.inbound.domain.LPNFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocationTest {

    @Test
    @DisplayName("로케이션에 LPN을 할당한다.")
    void assignLPN() {
        final Location location = LocationFixture.anLocationFixture().build();

        final LPN lpn = LPNFixture.anLPN().build();

        location.assignLPN(lpn);

        assertThat(location.getLocationLPNList()).hasSize(1);
        assertThat(location.getLocationLPNList().get(0).getInventoryQuantity()).isEqualTo(1L);

    }

}