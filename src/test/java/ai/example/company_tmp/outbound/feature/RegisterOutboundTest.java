package ai.example.company_tmp.outbound.feature;

import java.time.LocalDate;
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
            orderRepository.getBy(request.orderNo);
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

        public void getBy(final Long orderNo) {
            final OrderCustomer orderCustomer = new OrderCustomer();
            final String deliveryRequirements = "배송 요구사항";
            new Order(
                orderNo,
                orderCustomer,
                deliveryRequirements,
                )
        }
    }

    public class Order {

    }

    public class OrderCustomer {

    }
}
