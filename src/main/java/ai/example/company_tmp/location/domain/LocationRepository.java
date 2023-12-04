package ai.example.company_tmp.location.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class LocationRepository {

    private final Map<Long, Location> locations = new HashMap<>();
    private Long sequence = 1L;

    public void save(final Location location) {
        location.assignId(sequence++);
        locations.put(location.getLocationNo(), location);
    }

    public List<Location> findAll() {
        return new ArrayList<>(locations.values());
    }
}