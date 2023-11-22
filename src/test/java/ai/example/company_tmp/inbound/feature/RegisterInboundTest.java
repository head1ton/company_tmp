package ai.example.company_tmp.inbound.feature;

import static ai.example.company_tmp.product.fixture.ProductFixture.aProduct;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;

import ai.example.company_tmp.inbound.domain.InboundRepository;
import ai.example.company_tmp.product.domain.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RegisterInboundTest {

    private RegisterInbound registerInbound;
    private ProductRepository productRepository;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        inboundRepository = new InboundRepository();
        registerInbound = new RegisterInbound(productRepository, inboundRepository);
    }

    @Test
    @DisplayName("입고 등록")
    void registerInbound() {

        Mockito.when(productRepository.getBy(anyLong()))
               .thenReturn(aProduct().build());

        final LocalDateTime orderRequestedAt = LocalDateTime.now();
        final LocalDateTime estimatedArrivalAt = LocalDateTime.now().plusDays(1);
        final Long productNo = 1L;
        final Long quantity = 1L;
        final Long unitPrice = 1500L;
        final RegisterInbound.Request.Item inboundItem = new RegisterInbound.Request.Item(
            productNo,
            quantity,
            unitPrice,
            "description"
        );
        final List<RegisterInbound.Request.Item> inboundItems = List.of(inboundItem);

        final RegisterInbound.Request request = new RegisterInbound.Request(
            "title",
            "description",
            orderRequestedAt,
            estimatedArrivalAt,
            inboundItems
        );
        registerInbound.request(request);
    }

}
