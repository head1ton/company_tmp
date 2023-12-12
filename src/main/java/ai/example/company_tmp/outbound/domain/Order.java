package ai.example.company_tmp.outbound.domain;

import java.util.List;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class Order {

    public final Long orderNo;
    public final OrderCustomer orderCustomer;
    public final String deliveryRequirements;
    public final List<OrderProduct> orderProducts;

    public Order(
        final Long orderNo,
        final OrderCustomer orderCustomer,
        final String deliveryRequirements,
        final List<OrderProduct> orderProducts) {

        this.orderNo = orderNo;
        this.orderCustomer = orderCustomer;
        this.deliveryRequirements = deliveryRequirements;
        this.orderProducts = orderProducts;
    }

    public Long totalWeight() {
        return orderProducts.stream()
                            .mapToLong(OrderProduct::getWeight)
                            .sum();
    }

    public Long totalVolume() {
        return orderProducts.stream()
                            .mapToLong(OrderProduct::getVolume)
                            .sum();
    }
}
