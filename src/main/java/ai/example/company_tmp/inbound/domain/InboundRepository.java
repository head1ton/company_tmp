package ai.example.company_tmp.inbound.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class InboundRepository {

    private final Map<Long, Inbound> inbounds = new HashMap<>();
    private Long sequence = 1L;

    public void save(final Inbound inbound) {
        inbound.assignId(sequence++);
        inbounds.put(inbound.getId(), inbound);
    }

    public List<Inbound> findAll() {
        return new ArrayList<>(inbounds.values());
    }
}
