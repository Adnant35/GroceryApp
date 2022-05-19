package tr.com.kolaysoft.manav.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.kolaysoft.manav.repository.StockRepository;
import tr.com.kolaysoft.manav.service.StockService;
import tr.com.kolaysoft.manav.service.dto.StockDTO;
import tr.com.kolaysoft.manav.web.rest.StockResource;
import tr.com.kolaysoft.manav.web.rest.errors.BadRequestAlertException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StockResource {

    private final StockService stockService;

    private final StockRepository stockRepository;

    public StockResource(StockService stockService, StockRepository stockRepository) {
        this.stockService = stockService;
        this.stockRepository = stockRepository;
    }

    @PutMapping("/stocks")
    public ResponseEntity<StockDTO> updateStock(@Valid @RequestBody StockDTO stockDTO) {
        if (stockDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }

        if (!stockRepository.existsById(stockDTO.getId())) {
            throw new BadRequestAlertException("Entity not found");
        }

        StockDTO result = stockService.save(stockDTO);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping("/stocks")
    public List<StockDTO> getAllStocks() {
        return stockService.findAll();
    }


    @GetMapping("/stocks/{id}")
    public ResponseEntity<StockDTO> getStock(@PathVariable Long id) {
        Optional<StockDTO> stockDTO = stockService.findOne(id);
        return ResponseEntity.ok(stockDTO.orElse(null));
    }
    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
