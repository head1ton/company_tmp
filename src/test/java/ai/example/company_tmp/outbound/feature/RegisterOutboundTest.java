package ai.example.company_tmp.outbound.feature;

import ai.example.company_tmp.product.domain.Product;
import ai.example.company_tmp.product.domain.ProductRepository;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class RegisterOutboundTest {

    private RegisterOutbound registerOutbound;

    @BeforeEach
    void setUp() {
        registerOutbound = new RegisterOutbound();
    }

    @Test
    @DisplayName("출고를 등록한다.")
    void registerOutbound() {
        final Long orderNo = 1L;
        final Boolean isPriorityDelivery = false;
        final LocalDate desiredDeliveryAt = LocalDate.now();
        final RegisterOutbound.Request request = new RegisterOutbound.Request(
            orderNo,
            isPriorityDelivery,
            desiredDeliveryAt
        );

        registerOutbound.request(request);

        // TODO 출고가 등록되었는지 확인.
    }

    public class RegisterOutbound {

        private OrderRepository orderRepository;

        public void request(final Request request) {
            // 주문을 먼저 조회
            Order order = orderRepository.getBy(request.orderNo);
            // 주문 정보를 가져오고

            // 주문 정보에 맞는 상품의 재고가 충분한지 확인하고 충분하지 않으면 예외를 던진다.

            // 출고에 사용할 포장재를 선택해준다.

            // 출고를 생성하고.
            order.orderProducts)
            // 출고를 등록한다.

        }

        public record Request(
            Long orderNo,
            Boolean isPriorityDelivery,
            LocalDate desiredDeliveryAt) {

            public Request {
                Assert.notNull(orderNo, "주문번호는 필수입니다.");
                Assert.notNull(isPriorityDelivery, "우선출고여부는 필수입니다.");
                Assert.notNull(desiredDeliveryAt, "희망출고일은 필수입니다.");
            }
        }
    }

    public class OrderRepository {

        private ProductRepository productRepository;

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

    public class Order {

        private final Long orderNo;
        private final OrderCustomer orderCustomer;
        private final String deliveryRequirements;
        private final List<OrderProduct> orderProducts;

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
    }

    public class OrderCustomer {

        private final String name;
        private final String email;
        private final String phone;
        private final String zipNo;
        private final String address;

        public OrderCustomer(
            final String name,
            final String email,
            final String phone,
            final String zipNo,
            final String address) {

            this.name = name;
            this.email = email;
            this.phone = phone;
            this.zipNo = zipNo;
            this.address = address;
        }
    }

    public class OrderProduct {

        private final Product product;
        private final Long orderQuantity;
        private final Long unitPrice;

        public OrderProduct(
            final Product product,
            final Long orderQuantity,
            final Long unitPrice) {

            this.product = product;
            this.orderQuantity = orderQuantity;
            this.unitPrice = unitPrice;
        }
    }
}
