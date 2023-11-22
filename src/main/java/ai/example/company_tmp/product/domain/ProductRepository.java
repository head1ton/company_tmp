package ai.example.company_tmp.product.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
