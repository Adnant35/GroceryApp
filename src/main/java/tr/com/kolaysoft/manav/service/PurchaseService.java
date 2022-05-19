package tr.com.kolaysoft.manav.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.kolaysoft.manav.domain.Purchase;
import tr.com.kolaysoft.manav.repository.PurchaseRepository;
import tr.com.kolaysoft.manav.service.dto.ProductDTO;
import tr.com.kolaysoft.manav.service.dto.PurchaseDTO;
import tr.com.kolaysoft.manav.service.mapper.PurchaseMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final PurchaseMapper purchaseMapper;


    public PurchaseService(PurchaseRepository purchaseRepository, PurchaseMapper purchaseMapper){
        this.purchaseRepository= purchaseRepository;
        this.purchaseMapper = purchaseMapper;
    }
    public PurchaseDTO save(PurchaseDTO purchaseDTO){
        Purchase purchase=purchaseMapper.toEntity(purchaseDTO);
        purchase= purchaseRepository.save(purchase);
        return purchaseMapper.toDto(purchase);

    }

    @Transactional(readOnly = true)
    public List<PurchaseDTO> findAll() {
        return purchaseRepository.findAll().stream().map(purchaseMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<PurchaseDTO> findOne(Long id) {
        return purchaseRepository.findById(id).map(purchaseMapper::toDto);
    }

    public void delete(Long id) {
        purchaseRepository.deleteById(id);
    }

}
