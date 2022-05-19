package tr.com.kolaysoft.manav.web.rest;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.kolaysoft.manav.repository.SaleRepository;
import tr.com.kolaysoft.manav.service.SaleService;
import tr.com.kolaysoft.manav.service.dto.ProductSalePurchaseDTO;
import tr.com.kolaysoft.manav.service.dto.SaleDTO;
import tr.com.kolaysoft.manav.service.dto.StockDTO;
import tr.com.kolaysoft.manav.service.StockService;
import tr.com.kolaysoft.manav.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tr.com.kolaysoft.manav.domain.Sale}.
 */
@RestController
@RequestMapping("/api")
public class SaleResource {

    private final SaleService saleService;

    private final SaleRepository saleRepository;
    private final StockService stockService;

    public SaleResource(SaleService saleService, SaleRepository saleRepository,StockService stockService) {
        this.saleService = saleService;
        this.saleRepository = saleRepository;
        this.stockService=stockService;
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
    public StockDTO saleStock(Long id, Long productid, BigDecimal totalcount){
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

    /**
     * {@code POST  /sales} : Create a new sale.
     *
     * @param saleDTO the saleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new saleDTO, or with status {@code 400 (Bad Request)} if the sale has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales")
    public ResponseEntity<SaleDTO> createSale(@Valid @RequestBody SaleDTO saleDTO) throws URISyntaxException {
        if (saleDTO.getId() == null) {
            throw new BadRequestAlertException("A new sale cannot already have an ID");
        }
        SaleDTO result = saleService.save(saleDTO);

        StockDTO tempstockDTO=getStock(saleDTO.stocksale.productId);

        createStock(saleStock(saleDTO.stocksale.productId, saleDTO.stocksale.productId,tempstockDTO.totalcount.subtract( saleDTO.stocksale.getCount())));
        System.out.println(saleDTO.getProducts().stream().count());
        return ResponseEntity.created(new URI("/api/sales/" + result.getId())).body(result);
    }

    /**
     * {@code PUT  /sales} : Updates an existing sale.
     *
     * @param saleDTO the saleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated saleDTO,
     * or with status {@code 400 (Bad Request)} if the saleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the saleDTO couldn't be updated.
     */
    @PutMapping("/sales")
    public ResponseEntity<SaleDTO> updateSale(@Valid @RequestBody SaleDTO saleDTO) {
        if (saleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }

        if (!saleRepository.existsById(saleDTO.getId())) {
            throw new BadRequestAlertException("Entity not found");
        }

        SaleDTO result = saleService.save(saleDTO);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /sales} : get all the sales.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sales in body.
     */
    @GetMapping("/sales")
    public List<SaleDTO> getAllSales() {
        return saleService.findAll();
    }

    /**
     * {@code GET  /sales/:id} : get the "id" sale.
     *
     * @param id the id of the saleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the saleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales/{id}")
    public ResponseEntity<SaleDTO> getSale(@PathVariable Long id) {
        Optional<SaleDTO> saleDTO = saleService.findOne(id);
        return ResponseEntity.ok(saleDTO.orElse(null));
    }

    /**
     * {@code DELETE  /sales/:id} : delete the "id" sale.
     *
     * @param id the id of the saleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) throws URISyntaxException {

        StockDTO tempstockDTO=getStock(getSale(id).getBody().stocksale.productId);

        createStock(saleStock(getSale(id).getBody().stocksale.productId, getSale(id).getBody().stocksale.productId,tempstockDTO.totalcount.add(getSale(id).getBody().stocksale.count)));
          saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
