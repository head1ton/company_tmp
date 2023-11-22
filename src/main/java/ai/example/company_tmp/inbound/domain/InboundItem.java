package ai.example.company_tmp.inbound.domain;

import ai.example.company_tmp.product.domain.Product;
import org.springframework.util.Assert;

public class InboundItem {

    private final Product product;
    private final Long quantity;
    private final Long unitPrice;
    private final String description;

    public InboundItem(final Product product, final Long quantity, final Long unitPrice,
        final String description) {
        validateConstructor(product, quantity, unitPrice, description);

        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.description = description;

    }

    private static void validateConstructor(final Product product, final Long quantity,
        final Long unitPrice, final String description) {
        Assert.notNull(product, "product 필수입니다.");
        Assert.notNull(quantity, "quantity 필수입니다.");
        if (0 > quantity) {
            throw new IllegalArgumentException("quantity는 0보다 작을 수 없습니다.");
        }
        Assert.notNull(unitPrice, "unitPrice 필수입니다.");
        if (0 > unitPrice) {
            throw new IllegalArgumentException("unitPrice는 0보다 작을 수 없습니다.");
        }
        Assert.notNull(description, "description 필수입니다.");
    }
}
