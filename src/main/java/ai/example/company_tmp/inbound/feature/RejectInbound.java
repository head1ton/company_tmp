package ai.example.company_tmp.inbound.feature;

import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RejectInbound {

    private final InboundRepository inboundRepository;

    RejectInbound(final InboundRepository inboundRepository) {
        this.inboundRepository = inboundRepository;
    }

    @PostMapping("/inbounds/{inboundNo}/reject")
    @Transactional
    public void request(
        @PathVariable final Long inboundNo,
        @RequestBody @Valid final Request request) {
        final Inbound inbound = inboundRepository.getBy(inboundNo);

        inbound.reject(request.rejectionReason);
    }

    public record Request(
        @NotBlank(message = "거절 사유는 필수입니다.")
        String rejectionReason) {
    }
}
