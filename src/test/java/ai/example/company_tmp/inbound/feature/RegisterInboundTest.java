package ai.example.company_tmp.inbound.feature;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class RegisterInboundTest {

    private RegisterInbound registerInbound;

    @BeforeEach
    void setUp() {
        registerInbound = new RegisterInbound();
    }

    @Test
    @DisplayName("입고 등록")
    void registerInbound() {
        final LocalDateTime orderRequestedAt = LocalDateTime.now();
        final LocalDateTime estimatedArrivalAt = LocalDateTime.now().plusDays(1);
        final Long productNo = 1L;
        final Long quantity = 1L;
        final Long unitPrice = 1500L;
        RegisterInbound.Request.Item inboundItem = new RegisterInbound.Request.Item(
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

    public class RegisterInbound {

        public void request(final Request request) {

        }

        public record Request(String title, String description, LocalDateTime orderRequestedAt,
                              LocalDateTime estimatedArrivalAt, List<Item> inboundItems) {

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
}
