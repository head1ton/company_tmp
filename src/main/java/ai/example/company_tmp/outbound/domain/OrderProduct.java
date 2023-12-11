package ai.example.company_tmp.outbound.domain;

import ai.example.company_tmp.product.domain.Product;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class OrderProduct {

    public final Product product;
    public final Long orderQuantity;
    public final Long unitPrice;

    public OrderProduct(
        final Product product,
        final Long orderQuantity,
        final Long unitPrice) {

        this.product = product;
        this.orderQuantity = orderQuantity;
        this.unitPrice = unitPrice;
    }

    public Long getProductNo() {
        return product.getProductNo();
    }
}
