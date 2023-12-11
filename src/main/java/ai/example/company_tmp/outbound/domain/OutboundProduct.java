package ai.example.company_tmp.outbound.domain;

import ai.example.company_tmp.product.domain.Product;
import org.springframework.util.Assert;

public class OutboundProduct {

    private final Product product;
    private final Long orderQuantity;
    private final Long unitPrice;

    public OutboundProduct(
        final Product product,
        final Long orderQuantity,
        final Long unitPrice) {
        Assert.notNull(product, "상품은 필수입니다.");
        Assert.notNull(orderQuantity, "주문 수량은 필수입니다.");
        if (orderQuantity < 1) {
            throw new IllegalArgumentException("주문 수량은 1개 이상이어야 합니다..");
        }
        Assert.notNull(unitPrice, "단가는 필수입니다.");
        if (unitPrice < 1) {
            throw new IllegalArgumentException("주문 단가는 1원 이상이어야 합니다.");
        }
        this.product = product;
        this.orderQuantity = orderQuantity;
        this.unitPrice = unitPrice;
    }
}
