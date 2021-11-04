package uz.azizbek.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.azizbek.model.Address;
import uz.azizbek.model.Company;
import uz.azizbek.payload.AddressDto;
import uz.azizbek.payload.CompanyDto;
import uz.azizbek.utility.EntityMapper;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface CompanyMapper extends EntityMapper<CompanyDto, Company> {

    @Mapping(target = "addressDto", source = "address")
    CompanyDto toDto(Company company);
}
