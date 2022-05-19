package tr.com.kolaysoft.manav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.kolaysoft.manav.domain.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
}
