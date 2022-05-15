package tr.com.kolaysoft.manav.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tr.com.kolaysoft.manav.domain.Product;

/**
 * Spring Data SQL repository for the Product entity.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
