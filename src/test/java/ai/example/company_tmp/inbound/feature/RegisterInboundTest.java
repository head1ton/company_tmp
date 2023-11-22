package ai.example.company_tmp.inbound.feature;

import ai.example.company_tmp.inbound.domain.InboundRepository;
import ai.example.company_tmp.product.domain.Category;
import ai.example.company_tmp.product.domain.Product;
import ai.example.company_tmp.product.domain.ProductRepository;
import ai.example.company_tmp.product.domain.ProductSize;
import ai.example.company_tmp.product.domain.TemperatureZone;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
        productRepository = Mockito.mock(ProductRepository.class);
        inboundRepository = new InboundRepository();
        registerInbound = new RegisterInbound(productRepository, inboundRepository);
    }

    @Test
    @DisplayName("입고 등록")
    void registerInbound() {
        final Product product = new Product(
            "name",
            "code",
            "description",
            "brand",
            "maker",
            "origin",
            Category.ELECTRONICS,
            TemperatureZone.ROOM_TEMPERATURE,
            1000L,
            new ProductSize(100L, 100L, 100L)
        );

        Mockito.when(productRepository.findById(Mockito.anyLong()))
               .thenReturn(Optional.of(product));

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
