package ai.example.company_tmp.location.feature;

import ai.example.company_tmp.inbound.domain.LPN;
import ai.example.company_tmp.inbound.domain.LPNRepository;
import ai.example.company_tmp.location.domain.Location;
import ai.example.company_tmp.location.domain.LocationRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AssignLocationLPN {

    private final LocationRepository locationRepository;
    private final LPNRepository lpnRepository;

    @Transactional
    @PostMapping("/locations/assign-lpn")
    public void request(@RequestBody @Valid final Request request) {
        final Location location = locationRepository.getByLocationBarcode(
            request.locationBarcode);

        final LPN lpn = lpnRepository.getByLPNBarcode(request.lpnBarcode);

        location.assignLPN(lpn);
    }

    public record Request(String locationBarcode, String lpnBarcode) {

        public Request {
            Assert.hasText(locationBarcode, "로케이션 바코드는 필수입니다.");
            Assert.hasText(lpnBarcode, "LPN 바코드는 필수입니다.");
        }
    }
}
