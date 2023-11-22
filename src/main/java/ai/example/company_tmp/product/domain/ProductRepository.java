package ai.example.company_tmp.product.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private Long sequence = 1L;

    public void save(final Product product) {
        product.assignId(sequence++);
        products.put(product.getId(), product);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
}
