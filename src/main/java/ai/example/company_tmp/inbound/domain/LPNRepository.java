package ai.example.company_tmp.inbound.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LPNRepository extends JpaRepository<LPN, Long> {

    @Query("select l from LPN l where l.lpnBarcode = :lpnBarcode")
    Optional<LPN> findByLPNBarcode(@Param(value = "lpnBarcode") String lpnBarcode);

    default LPN getByLPNBarcode(final String lpnBarcode) {
        return findByLPNBarcode(lpnBarcode).orElseThrow();
    }
}
