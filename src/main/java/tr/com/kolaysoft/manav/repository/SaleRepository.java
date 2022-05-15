package tr.com.kolaysoft.manav.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tr.com.kolaysoft.manav.domain.Sale;

/**
 * Spring Data SQL repository for the Sale entity.
 */
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {}
