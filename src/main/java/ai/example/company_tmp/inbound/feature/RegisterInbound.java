package ai.example.company_tmp.inbound.feature;

import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundItem;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import ai.example.company_tmp.inbound.feature.RegisterInbound.Request.Item;
import ai.example.company_tmp.product.domain.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.util.Assert;

public class RegisterInbound {

    private final ProductRepository productRepository;
    private final InboundRepository inboundRepository;

    public RegisterInbound(final ProductRepository productRepository,
        final InboundRepository inboundRepository) {
        this.productRepository = productRepository;
        this.inboundRepository = inboundRepository;
    }

    public void request(final Request request) {
        final Inbound inbound = createInbound(request);

        inboundRepository.save(inbound);
    }

    private Inbound createInbound(final Request request) {
        return new Inbound(
            request.title,
            request.description,
            request.orderRequestedAt,
            request.estimatedArrivalAt,
            mapToInboundItems(
                request)
        );
    }

    private List<InboundItem> mapToInboundItems(final Request request) {
        return request.inboundItems.stream()
                                   .map(this::newInboundItem)
                                   .toList();
    }

    private InboundItem newInboundItem(final Item item) {
        return new InboundItem(
            productRepository.getBy(item.productNo),
            item.quantity,
            item.unitPrice,
            item.description
        );
    }

    public record Request(String title, String description, LocalDateTime orderRequestedAt,
                          LocalDateTime estimatedArrivalAt, List<Request.Item> inboundItems) {

        public Request {
            Assert.hasText(title, "title 필수입니다.");
            Assert.hasText(description, "description 필수입니다.");
            Assert.notNull(orderRequestedAt, "orderRequestedAt 필수입니다.");
            Assert.notNull(estimatedArrivalAt, "estimatedArrivalAt 필수입니다.");
            Assert.notEmpty(inboundItems, "입고 품목은 필수입니다.");
        }

        public record Item(Long productNo, Long quantity, Long unitPrice, String description) {

            public Item {
                Assert.notNull(productNo, "productNo 필수입니다.");
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
    }
}
