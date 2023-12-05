package ai.example.company_tmp.outbound.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackagingMaterialRepository {

    private final Map<Long, PackagingMaterial> packagingMaterialMap = new HashMap<>();
    private Long sequence = 1L;

    public void save(final PackagingMaterial packagingMaterial) {
        packagingMaterial.assignNo(sequence++);
        packagingMaterialMap.put(packagingMaterial.getPackagingMaterialNo(), packagingMaterial);
    }

    public List<PackagingMaterial> findAll() {
        return new ArrayList<>(packagingMaterialMap.values());
    }
}
