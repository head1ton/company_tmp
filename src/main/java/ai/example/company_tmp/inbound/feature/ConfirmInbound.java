package ai.example.company_tmp.inbound.feature;

import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfirmInbound {

    private final InboundRepository inboundRepository;

    @PostMapping("/inbounds/{inboundNo}/confirm")
    @Transactional
    public void request(@PathVariable final Long inboundNo) {
        final Inbound inbound = inboundRepository.getBy(inboundNo);

        inbound.confirmed();
    }
}
