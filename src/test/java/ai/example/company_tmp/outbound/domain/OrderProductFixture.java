package ai.example.company_tmp.outbound.domain;

import ai.example.company_tmp.product.fixture.ProductFixture;

public class OrderProductFixture {

    private final ProductFixture productFixture = ProductFixture.aProduct();
    private Long orderQuantity = 1L;
    private Long unitPrice = 1500L;

    public static OrderProductFixture anOrderProduct() {
        return new OrderProductFixture();
    }

    public OrderProductFixture orderQuantity(final Long orderQuantity) {
        this.orderQuantity = orderQuantity;
        return this;
    }

    public OrderProductFixture unitPrice(final Long unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public OrderProduct build() {
        return new OrderProduct(
            productFixture.build(),
            orderQuantity,
            unitPrice
        );
    }
}