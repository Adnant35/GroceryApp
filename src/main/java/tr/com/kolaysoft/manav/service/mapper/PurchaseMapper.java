package tr.com.kolaysoft.manav.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tr.com.kolaysoft.manav.domain.Purchase;
import tr.com.kolaysoft.manav.service.dto.PurchaseDTO;


@Mapper(componentModel = "spring", uses={})
public interface PurchaseMapper extends EntityMapper<PurchaseDTO, Purchase> {

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PurchaseDTO toDtoId(Purchase purchase);

}
