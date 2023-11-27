package ai.example.company_tmp.inbound.feature;

import ai.example.company_tmp.inbound.domain.Inbound;
import ai.example.company_tmp.inbound.domain.InboundRepository;

public class RejectInbound {

    private final InboundRepository inboundRepository;

    RejectInbound(final InboundRepository inboundRepository) {
        this.inboundRepository = inboundRepository;
    }

    public void request(final Long inboundNo, final Request request) {
        final Inbound inbound = inboundRepository.getBy(inboundNo);

        inbound.reject(request.rejectionReason);
    }

    public record Request(String rejectionReason) {

    }
}
