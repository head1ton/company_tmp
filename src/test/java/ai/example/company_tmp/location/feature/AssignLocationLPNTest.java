package ai.example.company_tmp.location.feature;

import ai.example.company_tmp.location.domain.Location;
import ai.example.company_tmp.location.domain.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class AssignLocationLPNTest {

    private AssignLocationLPN assignLocationLPN;

    @BeforeEach
    void setUp() {
        assignLocationLPN = new AssignLocationLPN();
    }

    @Test
    @DisplayName("로케이션에 LPN을 할당한다.")
    void assignLocationLPN() {
        final String locationBarcode = "A-1-1";
        final String lpnBarcode = "LPN-1";
        final AssignLocationLPN.Request request = new AssignLocationLPN.Request(
            locationBarcode,
            lpnBarcode
        );

        assignLocationLPN.request(request);


    }

    public class AssignLocationLPN {

        private LocationRepository locationRepository;

        public void request(final Request request) {
            final Location location = locationRepository.getByLocationBarcode(
                request.locationBarcode);
        }

        public record Request(String locationBarcode, String lpnBarcode) {

            public Request {
                Assert.hasText(locationBarcode, "로케이션 바코드는 필수입니다.");
                Assert.hasText(lpnBarcode, "LPN 바코드는 필수입니다.");
            }
        }
    }
}
