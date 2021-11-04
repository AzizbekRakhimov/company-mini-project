package uz.azizbek.service.mapper;

import org.mapstruct.Mapper;
import uz.azizbek.model.Address;
import uz.azizbek.payload.AddressDto;
import uz.azizbek.utility.EntityMapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDto, Address> {
}
