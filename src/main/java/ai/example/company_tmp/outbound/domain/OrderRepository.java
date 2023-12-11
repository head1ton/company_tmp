package ai.example.company_tmp.outbound.domain;

import ai.example.company_tmp.product.domain.ProductRepository;
import java.util.Collections;
import org.springframework.stereotype.Component;

@Component
public class OrderRepository {

    private final ProductRepository productRepository;

    public OrderRepository(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Order getBy(final Long orderNo) {
        return new Order(
            orderNo,
            new OrderCustomer(
                "name",
                "email",
                "phone",
                "zipNo",
                "address"
            ),
            "배송 요구사항",
            Collections.singletonList(new OrderProduct(
                productRepository.getBy(1L),
                1500L,
                1L
            ))
        );
    }
}
