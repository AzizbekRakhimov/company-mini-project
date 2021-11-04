package uz.azizbek.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.azizbek.model.Address;
import uz.azizbek.model.Company;
import uz.azizbek.model.Department;
import uz.azizbek.payload.AddressDto;
import uz.azizbek.payload.CompanyDto;
import uz.azizbek.payload.DepartmentDto;
import uz.azizbek.utility.EntityMapper;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface DepartmentMapper extends EntityMapper<DepartmentDto, Department> {

    @Mapping(target = "companyDto" , source = "company")
    DepartmentDto toDto(Department department);
}
