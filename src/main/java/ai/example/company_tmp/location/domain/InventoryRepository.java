package ai.example.company_tmp.location.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("select i from Inventory i where i.productNo = :productNo")
    List<Inventory> findByProductNo(@Param(value = "productNo") Long productNo);
}