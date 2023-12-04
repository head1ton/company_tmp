package ai.example.company_tmp.location.feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AssignLocationLPNTest {

    private AssignLocationLPN assignLocationLPN;

    @BeforeEach
    void setUp() {
        assignLocationLPN = new AssignLocationLPN();
    }

    @Test
    @DisplayName("로케이션에 LPN을 할당한다.")
    void assignLocationLPN() {
        assignLocationLPN.request();
    }

    public class AssignLocationLPN {

        public void request() {
            throw new UnsupportedOperationException("Unsupported request");
        }
    }
}