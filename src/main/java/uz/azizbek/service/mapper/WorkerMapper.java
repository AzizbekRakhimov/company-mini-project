package uz.azizbek.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.azizbek.model.Address;
import uz.azizbek.model.Worker;
import uz.azizbek.payload.AddressDto;
import uz.azizbek.payload.WorkerDto;
import uz.azizbek.utility.EntityMapper;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, DepartmentMapper.class})
public interface WorkerMapper extends EntityMapper<WorkerDto, Worker> {
    @Mapping(source = "address", target = "addressDto")
    @Mapping(source = "department", target = "departmentDto")
    WorkerDto toDto(Worker worker);
}
