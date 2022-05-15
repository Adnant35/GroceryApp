package tr.com.kolaysoft.manav.service.mapper;

import org.mapstruct.*;
import tr.com.kolaysoft.manav.domain.Sale;
import tr.com.kolaysoft.manav.domain.SaleProduct;
import tr.com.kolaysoft.manav.service.dto.SaleDTO;
import tr.com.kolaysoft.manav.service.dto.ProductSalePurchaseDTO;

/**
 * Mapper for the entity {@link Sale} and its DTO {@link SaleDTO}.
 */
@Mapper(componentModel = "spring", uses = { GroceryMapper.class })
public interface SaleMapper extends EntityMapper<SaleDTO, Sale> {

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "removeProduct", ignore = true)
    Sale toEntity(SaleDTO dto);

    @Mapping(target = "grocery", source = "grocery", qualifiedByName = "id")
     SaleDTO toDto(Sale s);

    @Mapping(target = "productId", source = "product.id")
    ProductSalePurchaseDTO map(SaleProduct saleProduct);

}
