package ai.example.company_tmp.outbound.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutboundRepository {

    private final Map<Long, Outbound> outbounds = new HashMap<>();
    private Long sequence = 1L;

    public void save(final Outbound outbound) {
        outbound.assignNo(sequence++);
        outbounds.put(outbound.getOutboundNo(), outbound);
    }

    public List<Outbound> findAll() {
        return new ArrayList<>(outbounds.values());
    }
}
