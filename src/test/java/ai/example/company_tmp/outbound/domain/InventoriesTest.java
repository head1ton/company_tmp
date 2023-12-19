package ai.example.company_tmp.outbound.domain;

class InventoriesTest {

//    @Test
//    @DisplayName("주문한 상품을 출고할 수 있는 재고가 있는지 확인한다.")
//    void validateInventory() {
//        final Inventory inventory = new Inventory(
//            LocationFixture.aLocation().build(),
//            LPNFixture.anLPN().build()
//        );
//        new Inventories(List.of(inventory), 1L).validateInventory(orderProduct.orderQuantity());
//    }
//
//    @Test
//    @DisplayName("주문한 상품이 출고할 수 있는 재고보다 많다면")
//    void fail_over_quantity_validateInventory() {
//        final Inventory inventory = new Inventory(
//            LocationFixture.aLocation().build(),
//            LPNFixture.anLPN().build()
//        );
//
//        assertThatThrownBy(() -> {
//            new Inventories(List.of(inventory), 2L).validateInventory(orderProduct.orderQuantity());
//        }).isInstanceOf(IllegalArgumentException.class)
//          .hasMessageContaining("재고가 부족합니다.");
//    }
//
//    @Test
//    @DisplayName("유통기한이 지났으면 재고가 있어도")
//    void expire_validateInventory() {
//        final Location location = LocationFixture.aLocation().build();
//        final LPN lpn = LPNFixture.anLPN().expirationAt(LocalDateTime.now().minusDays(1)).build();
//        final Inventory inventory = new Inventory(
//            location,
//            lpn
//        );
//
//        assertThatThrownBy(() -> {
//            new Inventories(List.of(inventory), 1L).validateInventory(orderProduct.orderQuantity());
//        }).isInstanceOf(IllegalArgumentException.class)
//          .hasMessageContaining("재고가 부족합니다.");
//    }
}