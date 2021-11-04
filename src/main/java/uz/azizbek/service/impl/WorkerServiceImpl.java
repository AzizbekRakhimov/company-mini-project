package uz.azizbek.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.azizbek.model.Address;
import uz.azizbek.model.Department;
import uz.azizbek.model.Worker;
import uz.azizbek.payload.WorkerDto;
import uz.azizbek.repository.WorkerRepository;
import uz.azizbek.service.AddressService;
import uz.azizbek.service.DepartmentService;
import uz.azizbek.service.WorkerService;
import uz.azizbek.service.mapper.WorkerMapper;

import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;
    private final DepartmentService departmentService;
    private final AddressService addressService;

    public WorkerServiceImpl(WorkerRepository workerRepository, WorkerMapper workerMapper, DepartmentService departmentService, AddressService addressService) {
        this.workerRepository = workerRepository;
        this.workerMapper = workerMapper;
        this.departmentService = departmentService;
        this.addressService = addressService;
    }

    @Override
    public Page<WorkerDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(workerMapper::toDto);
    }

    @Override
    public Page<Worker> findAll(Pageable pageable) {
        return workerRepository.findAll(pageable);
    }

    @Override
    public boolean existById(Long id) {
        return workerRepository.existsById(id);
    }

    @Override
    public Optional<WorkerDto> findOne(Long id) {
        return findById(id).map(workerMapper::toDto);
    }

    @Override
    public Optional<Worker> findById(Long id) {
        return workerRepository.findById(id);
    }

    @Override
    public WorkerDto create(WorkerDto workerDto) {

        Optional<Address> address = addressService.findById(workerDto.getAddressDto().getId());
        Optional<Department> department = departmentService.findById(workerDto.getDepartmentDto().getId());

        Worker worker = new Worker();
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setName(workerDto.getName());
        address.ifPresent(worker::setAddress);
        department.ifPresent(worker::setDepartment);
        return workerMapper.toDto(save(worker));
    }

    @Override
    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public WorkerDto update(Worker worker, WorkerDto workerDto) {
        Optional<Address> address = addressService.findById(workerDto.getAddressDto().getId());
        Optional<Department> department = departmentService.findById(workerDto.getDepartmentDto().getId());

        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setName(workerDto.getName());
        address.ifPresent(worker::setAddress);
        department.ifPresent(worker::setDepartment);
        return workerMapper.toDto(save(worker));
    }

    @Override
    public void delete(Long id) {
        workerRepository.deleteById(id);
    }
}
