package ai.example.company_tmp.location.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("select l from Location l where l.locationBarcode = :locationBarcode")
    Optional<Location> findByLocationBarcode(
        @Param(value = "locationBarcode") String locationBarcode);

    default Location getByLocationBarcode(final String locationBarcode) {
        return findByLocationBarcode(locationBarcode)
            .orElseThrow(() -> new IllegalArgumentException(
                "해당 로케이션을 찾을 수 없습니다. %s".formatted(locationBarcode)));
    }
}
