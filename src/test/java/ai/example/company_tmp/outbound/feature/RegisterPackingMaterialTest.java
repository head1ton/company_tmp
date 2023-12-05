package ai.example.company_tmp.outbound.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegisterPackingMaterialTest {

    private RegisterPackingMaterial registerPackingMaterial;

    @BeforeEach
    void setUp() {
        registerPackingMaterial = new RegisterPackingMaterial();
    }

    @Test
    @DisplayName("포장재를 등록한다.")
    void registerPackingMaterial() {
        final RegisterPackingMaterial.Request request = new RegisterPackingMaterial.Request();
        registerPackingMaterial.request(request);
    }

    private class RegisterPackingMaterial {

        public void request(final Request request) {
            throw new UnsupportedOperationException("Unsupported request");
        }

        public record Request() {

        }
    }
}
