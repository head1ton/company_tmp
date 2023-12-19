package ai.example.company_tmp.outbound.feature;

import ai.example.company_tmp.location.domain.Inventory;
import java.time.LocalDateTime;
import java.util.List;

public record Inventories(List<Inventory> inventories, Long orderQuantity) {

    void validateInventory() {
        final long totalInventoryQuantity = inventories().stream()
                                                         .filter(i -> i.getInventoryQuantity() > 0L)
                                                         .filter(i -> i.getLpn().getExpirationAt()
                                                                       .isAfter(
                                                                           LocalDateTime.now()))
                                                         .mapToLong(Inventory::getInventoryQuantity)
                                                         .sum();
        // 재고가 주문한 수량보다 적으면 예외를 던진다
        if (totalInventoryQuantity < orderQuantity()) {
            throw new IllegalArgumentException(
                "재고가 부족합니다. 재고 수량: %d, 주문 수량: %d".formatted(totalInventoryQuantity,
                    orderQuantity())
            );
        }
    }
}