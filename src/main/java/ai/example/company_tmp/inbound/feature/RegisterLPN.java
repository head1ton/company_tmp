package ai.example.company_tmp.inbound.feature;

import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import ai.example.company_tmp.inbound.domain.LPNRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterLPN {

    private final InboundRepository inboundRepository;
    private final LPNRepository lPNRepository;


    @PostMapping("/inbounds/inbound-items/{inboundItemNo}/lpn")
    @Transactional
    public void request(
        @PathVariable(name = "inboundItemNo") final Long inboundItemNo,
        @RequestBody @Valid final Request request) {

        checkIfLPNBarcodeAlreadyExists(request);

        final Inbound inbound = inboundRepository.getByInboundItemNo(inboundItemNo);

        inbound.registerLPN(
            inboundItemNo,
            request.lpnBarcode,
            request.expirationAt);
    }

    private void checkIfLPNBarcodeAlreadyExists(final Request request) {
        lPNRepository.findByLPNBarcode(request.lpnBarcode).ifPresent(
            lpn -> {
                throw new LPNBarcodeAlreadyExistsException(request.lpnBarcode);
            }
        );
    }

    public record Request(
        @NotBlank(message = "LPN 바코드는 필수입니다.")
        String lpnBarcode,
        @NotNull(message = "유통기한은 필수입니다.")
        LocalDateTime expirationAt) {
    }
}
