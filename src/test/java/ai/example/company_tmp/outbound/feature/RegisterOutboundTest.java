package ai.example.company_tmp.outbound.feature;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;

import ai.example.company_tmp.outbound.domain.OrderRepository;
import ai.example.company_tmp.outbound.domain.OutboundRepository;
import ai.example.company_tmp.product.domain.ProductRepository;
import ai.example.company_tmp.product.fixture.ProductFixture;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RegisterOutboundTest {

    private RegisterOutbound registerOutbound;
    private OrderRepository orderRepository;
    private OutboundRepository outboundRepository;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        orderRepository = new OrderRepository(productRepository);
        outboundRepository = new OutboundRepository();
        registerOutbound = new RegisterOutbound(orderRepository, outboundRepository);
    }

    @Test
    @DisplayName("출고를 등록한다.")
    void registerOutbound() {
        Mockito.when(productRepository.getBy(anyLong()))
               .thenReturn(ProductFixture.aProduct().build());

        final Long orderNo = 1L;
        final Boolean isPriorityDelivery = false;
        final LocalDate desiredDeliveryAt = LocalDate.now();
        final RegisterOutbound.Request request = new RegisterOutbound.Request(
            orderNo,
            isPriorityDelivery,
            desiredDeliveryAt
        );

        registerOutbound.request(request);

        assertThat(outboundRepository.findAll()).hasSize(1);
    }

}
