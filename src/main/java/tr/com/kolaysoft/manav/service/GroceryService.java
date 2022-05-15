package tr.com.kolaysoft.manav.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.kolaysoft.manav.domain.Grocery;
import tr.com.kolaysoft.manav.repository.GroceryRepository;
import tr.com.kolaysoft.manav.service.dto.GroceryDTO;
import tr.com.kolaysoft.manav.service.mapper.GroceryMapper;

/**
 * Service Implementation for managing {@link Grocery}.
 */
@Service
@Transactional
public class GroceryService {

    private final GroceryRepository groceryRepository;

    private final GroceryMapper groceryMapper;

    public GroceryService(GroceryRepository groceryRepository, GroceryMapper groceryMapper) {
        this.groceryRepository = groceryRepository;
        this.groceryMapper = groceryMapper;
    }

    /**
     * Save a grocery.
     *
     * @param groceryDTO the entity to save.
     * @return the persisted entity.
     */
    public GroceryDTO save(GroceryDTO groceryDTO) {
        Grocery grocery = groceryMapper.toEntity(groceryDTO);
        grocery = groceryRepository.save(grocery);
        return groceryMapper.toDto(grocery);
    }

    /**
     * Get all the groceries.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GroceryDTO> findAll() {
        return groceryRepository.findAll().stream().map(groceryMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one grocery by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GroceryDTO> findOne(Long id) {
        return groceryRepository.findById(id).map(groceryMapper::toDto);
    }

    /**
     * Delete the grocery by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        groceryRepository.deleteById(id);
    }
}
