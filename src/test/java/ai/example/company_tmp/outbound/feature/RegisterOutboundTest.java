package ai.example.company_tmp.outbound.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegisterOutboundTest {

    private RegisterOutbound registerOutbound;

    @BeforeEach
    void setUp() {
        registerOutbound = new RegisterOutbound();
    }

    @Test
    @DisplayName("출고를 등록한다.")
    void registerOutbound() {
        final RegisterOutbound.Request request = new RegisterOutbound.Request();
        registerOutbound.request(request);
    }

    public class RegisterOutbound {

        public void request(final Request request) {
            throw new UnsupportedOperationException("Unsupported request");
        }

        public record Request() {

        }
    }
}
