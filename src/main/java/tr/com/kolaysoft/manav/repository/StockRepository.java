package tr.com.kolaysoft.manav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.kolaysoft.manav.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
