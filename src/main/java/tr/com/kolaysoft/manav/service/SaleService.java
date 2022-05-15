package tr.com.kolaysoft.manav.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.kolaysoft.manav.domain.Product;
import tr.com.kolaysoft.manav.domain.Sale;
import tr.com.kolaysoft.manav.domain.SaleProduct;
import tr.com.kolaysoft.manav.repository.ProductRepository;
import tr.com.kolaysoft.manav.repository.SaleRepository;
import tr.com.kolaysoft.manav.service.dto.SaleDTO;
import tr.com.kolaysoft.manav.service.mapper.SaleMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Sale}.
 */
@Service
@Transactional
public class SaleService {

    private final SaleRepository saleRepository;

    private final SaleMapper saleMapper;

    private final ProductRepository productRepository;


    public SaleService(SaleRepository saleRepository, SaleMapper saleMapper, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.productRepository = productRepository;
    }

    /**
     * Save a sale.
     *
     * @param saleDTO the entity to save.
     * @return the persisted entity.
     */
    public SaleDTO save(SaleDTO saleDTO) {
        Sale sale = saleMapper.toEntity(saleDTO);
        final Set<SaleProduct> products = saleDTO
            .getProducts()
            .stream()
            .map(product -> new SaleProduct()
                .product(productRepository.findById(product.getProductId()).orElse(null))
                .count(product.getCount())
                .price(product.getPrice())
            )
            .collect(Collectors.toSet());
        sale.setProducts(products);
        sale = saleRepository.save(sale);
        return saleMapper.toDto(sale);
    }

    /**
     * Get all the sales.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SaleDTO> findAll() {
        return saleRepository.findAll()
                             .stream()
                             .map(saleMapper::toDto)
                             .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one sale by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SaleDTO> findOne(Long id) {
        return saleRepository.findById(id).map(saleMapper::toDto);
    }

    /**
     * Delete the sale by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        saleRepository.deleteById(id);
    }
}
