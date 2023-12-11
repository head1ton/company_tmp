package ai.example.company_tmp.outbound.feature;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import ai.example.company_tmp.inbound.domain.LPN;
import ai.example.company_tmp.inbound.domain.LPNFixture;
import ai.example.company_tmp.location.domain.Inventory;
import ai.example.company_tmp.location.domain.Location;
import ai.example.company_tmp.location.domain.LocationFixture;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegisterOutboundUnitTest {

    private RegisterOutbound registerOutbound;

    @BeforeEach
    void setUp() {
        registerOutbound = new RegisterOutbound(null, null, null);
    }

    @Test
    @DisplayName("주문한 상품을 출고할 수 있는 재고가 있는지 확인한다.")
    void validateInventory() {
        final Inventory inventory = new Inventory(
            LocationFixture.aLocation().build(),
            LPNFixture.anLPN().build()
        );
        registerOutbound.validateInventory(List.of(inventory), 1L);
    }

    @Test
    @DisplayName("주문한 상품이 출고할 수 있는 재고보다 많다면")
    void fail_over_quantity_validateInventory() {
        final Inventory inventory = new Inventory(
            LocationFixture.aLocation().build(),
            LPNFixture.anLPN().build()
        );

        assertThatThrownBy(() -> {
            registerOutbound.validateInventory(List.of(inventory), 2L);
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("재고가 부족합니다.");
    }

    @Test
    @DisplayName("유통기한이 지났으면 재고가 있어도")
    void expire_validateInventory() {
        final Location location = LocationFixture.aLocation().build();
        final LPN lpn = LPNFixture.anLPN().expirationAt(LocalDateTime.now().minusDays(1)).build();
        final Inventory inventory = new Inventory(
            location,
            lpn
        );

        assertThatThrownBy(() -> {
            registerOutbound.validateInventory(List.of(inventory), 1L);
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("재고가 부족합니다.");
    }
}
