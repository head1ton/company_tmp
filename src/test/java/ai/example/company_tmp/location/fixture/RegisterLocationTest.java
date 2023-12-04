package ai.example.company_tmp.location.fixture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegisterLocationTest {

    private RegisterLocation registerLocation;

    @BeforeEach
    void setUp() {
        registerLocation = new RegisterLocation();
    }

    @Test
    @DisplayName("로케이션을 등록한다.")
    void registerLocation() {
        registerLocation.request();
    }

    public class RegisterLocation {

        public void request() {
            throw new UnsupportedOperationException("Unsupported request");
        }
    }
}
