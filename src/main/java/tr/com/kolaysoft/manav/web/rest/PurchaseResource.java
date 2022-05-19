package tr.com.kolaysoft.manav.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.kolaysoft.manav.domain.Stock;
import tr.com.kolaysoft.manav.repository.PurchaseRepository;
import tr.com.kolaysoft.manav.service.PurchaseService;
import tr.com.kolaysoft.manav.service.dto.PurchaseDTO;
import tr.com.kolaysoft.manav.service.StockService;
import tr.com.kolaysoft.manav.service.dto.StockDTO;
import tr.com.kolaysoft.manav.web.rest.errors.BadRequestAlertException;


import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PurchaseResource {
    private final PurchaseService purchaseService;

    private final StockService stockService;
    private final PurchaseRepository purchaseRepository;


    public PurchaseResource(PurchaseService purchaseService, StockService stockService, PurchaseRepository purchaseRepository) {
        this.purchaseService = purchaseService;
        this.stockService = stockService;
        this.purchaseRepository = purchaseRepository;
    }

    @PostMapping("/purchases")
    public ResponseEntity<PurchaseDTO> createPurchase(@Valid @RequestBody PurchaseDTO purchaseDTO) throws URISyntaxException {
        if (purchaseDTO.getId() == null) {
            throw new BadRequestAlertException("A new grocery cannot already have an ID");
        }
        PurchaseDTO result = purchaseService.save(purchaseDTO);

        StockDTO tempstockDTO=getStock(purchaseDTO.getProductid());

        createStock(purchaseStock(purchaseDTO.getProductid(), purchaseDTO.getProductid(),purchaseDTO.getCount().add(tempstockDTO.totalcount)));

        return ResponseEntity.created(new URI("/api/purchases/" + result.getId())).body(result);
    }

    public ResponseEntity<StockDTO> createStock(@Valid @RequestBody StockDTO stockDTO) throws URISyntaxException {
        if (stockDTO.getId() == null) {
            throw new BadRequestAlertException("A new stock cannot already have an ID");
        }
        StockDTO result = stockService.save(stockDTO);
        return ResponseEntity.created(new URI("/api/stocks/" + result.getId())).body(result);
    }

    public StockDTO getStock(@PathVariable Long id) {
        Optional<StockDTO> stockDTO = stockService.findOne(id);
        return (stockDTO.orElse(null));
    }
    public StockDTO purchaseStock(Long id, Long productid, BigDecimal totalcount){
        StockDTO stockDTO=new StockDTO();
        stockDTO.id=id;
        stockDTO.productid=productid;
        stockDTO.totalcount=totalcount;
        return stockDTO;

    }
    public StockDTO StockDTOTemp(Long id,Long productid,BigDecimal totalcount){
        StockDTO stockDTO=new StockDTO();
        stockDTO.id=id;
        stockDTO.productid=productid;
        stockDTO.totalcount=totalcount;
        return stockDTO;

    }
    @PutMapping("/purchases")
    public ResponseEntity<PurchaseDTO> updatePurchase(@Valid @RequestBody PurchaseDTO purchaseDTO) throws URISyntaxException {
        if (purchaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }

        if (!purchaseRepository.existsById(purchaseDTO.getId())) {
            throw new BadRequestAlertException("Entity not found");
        }
        PurchaseDTO result = purchaseService.save(purchaseDTO);

        return ResponseEntity.ok().body(result);

    }



    @GetMapping("/purchases")
    public List<PurchaseDTO> getAllPurchases() {
        return purchaseService.findAll();
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<PurchaseDTO> getPurchase(@PathVariable Long id) {
        Optional<PurchaseDTO> purchaseDTO = purchaseService.findOne(id);
        return ResponseEntity.ok(purchaseDTO.orElse(null));
    }

    @DeleteMapping("/purchases/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) throws URISyntaxException {

        StockDTO tempstockDTO=getStock(getPurchase(id).getBody().getProductid());

        createStock(purchaseStock(getPurchase(id).getBody().getProductid(), getPurchase(id).getBody().getProductid(),tempstockDTO.totalcount.subtract(getPurchase(id).getBody().getCount())));
        purchaseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


