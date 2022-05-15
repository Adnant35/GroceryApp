package tr.com.kolaysoft.manav.service.mapper;

import org.mapstruct.*;
import tr.com.kolaysoft.manav.domain.Product;
import tr.com.kolaysoft.manav.service.dto.ProductDTO;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> { }
