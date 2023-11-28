package ai.example.company_tmp.inbound.feature;

import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterLPN {

    private final InboundRepository inboundRepository;

    public RegisterLPN(final InboundRepository inboundRepository) {
        this.inboundRepository = inboundRepository;
    }

    @PostMapping("/inbounds/inbound-items/{inboundItemNo}/lpn")
    @Transactional
    public void request(
        @PathVariable(name = "inboundItemNo") final Long inboundItemNo,
        @RequestBody @Valid final Request request) {
        final Inbound inbound = inboundRepository.getByInboundItemNo(inboundItemNo);

        inbound.registerLPN(
            inboundItemNo,
            request.lpnBarcode,
            request.expirationAt);
    }

    public record Request(
        @NotBlank(message = "LPN 바코드는 필수입니다.")
        String lpnBarcode,
        @NotNull(message = "유통기한은 필수입니다.")
        LocalDateTime expirationAt) {
    }
}
