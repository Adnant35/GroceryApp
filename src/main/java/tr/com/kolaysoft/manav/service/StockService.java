package tr.com.kolaysoft.manav.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.kolaysoft.manav.domain.Stock;
import tr.com.kolaysoft.manav.repository.StockRepository;
import tr.com.kolaysoft.manav.service.dto.StockDTO;
import tr.com.kolaysoft.manav.service.mapper.StockMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StockService {

    private final StockRepository stockRepository;

    private final StockMapper stockMapper;

    public StockService(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    public StockDTO save(StockDTO stockDTO) {
        Stock stock = stockMapper.toEntity(stockDTO);
        stock = stockRepository.save(stock);
        return stockMapper.toDto(stock);
    }
    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return stockRepository.findAll().stream().map(stockMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
    @Transactional(readOnly = true)
    public Optional<StockDTO> findOne(Long id) {
        return stockRepository.findById(id).map(stockMapper::toDto);
    }

    public void delete(Long id) {
        stockRepository.deleteById(id);
    }

}
