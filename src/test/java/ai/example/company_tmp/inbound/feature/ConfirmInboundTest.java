package ai.example.company_tmp.inbound.feature;

import static org.assertj.core.api.Assertions.assertThat;

import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundItem;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import ai.example.company_tmp.inbound.domain.InboundStatus;
import ai.example.company_tmp.product.fixture.ProductFixture;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ConfirmInboundTest {

    private ConfirmInbound confirmInbound;
    private InboundRepository inboundRepository;

    @BeforeEach
    void setUp() {
        inboundRepository = Mockito.mock(InboundRepository.class);
        confirmInbound = new ConfirmInbound(inboundRepository);
    }

    @Test
    @DisplayName("입고 승인")
    void confirmInbound() {
        final Long inboundNo = 1L;

        final Inbound inbound = new Inbound(
            "상품명",
            "상품코드",
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(1),
            List.of(new InboundItem(
                ProductFixture.aProduct().build(),
                1L,
                1500L,
                "description"
            ))
        );

        Mockito.when(inboundRepository.getBy(inboundNo))
               .thenReturn(inbound);

        confirmInbound.request(inboundNo);

        assertThat(inbound.getStatus()).isEqualTo(InboundStatus.CONFIRMED);
    }

    public class ConfirmInbound {

        private final InboundRepository inboundRepository;

        private ConfirmInbound(final InboundRepository inboundRepository) {
            this.inboundRepository = inboundRepository;
        }

        public void request(final Long inboundNo) {
            final Inbound inbound = inboundRepository.getBy(inboundNo);

            inbound.confirmed();
        }
    }
}
