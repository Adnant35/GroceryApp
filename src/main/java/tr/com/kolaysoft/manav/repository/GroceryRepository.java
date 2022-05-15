package tr.com.kolaysoft.manav.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tr.com.kolaysoft.manav.domain.Grocery;

/**
 * Spring Data SQL repository for the Grocery entity.
 */
@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Long> {}
