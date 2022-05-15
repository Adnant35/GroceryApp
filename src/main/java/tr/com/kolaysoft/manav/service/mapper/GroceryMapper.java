package tr.com.kolaysoft.manav.service.mapper;

import org.mapstruct.*;
import tr.com.kolaysoft.manav.domain.Grocery;
import tr.com.kolaysoft.manav.service.dto.GroceryDTO;

/**
 * Mapper for the entity {@link Grocery} and its DTO {@link GroceryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GroceryMapper extends EntityMapper<GroceryDTO, Grocery> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GroceryDTO toDtoId(Grocery grocery);
}
