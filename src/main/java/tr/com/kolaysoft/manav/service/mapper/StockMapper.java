package tr.com.kolaysoft.manav.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tr.com.kolaysoft.manav.domain.Stock;
import tr.com.kolaysoft.manav.service.dto.StockDTO;
@Mapper(componentModel = "spring", uses = {})
public interface StockMapper extends EntityMapper<StockDTO, Stock> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StockDTO toDtoId(Stock stock);
}
