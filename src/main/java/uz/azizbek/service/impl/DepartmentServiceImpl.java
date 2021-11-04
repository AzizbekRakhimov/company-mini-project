package uz.azizbek.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.azizbek.model.Company;
import uz.azizbek.model.Department;
import uz.azizbek.payload.DepartmentDto;
import uz.azizbek.repository.DepartmentRepository;
import uz.azizbek.service.CompanyService;
import uz.azizbek.service.DepartmentService;
import uz.azizbek.service.mapper.DepartmentMapper;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final CompanyService companyService;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper, CompanyService companyService) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
        this.companyService = companyService;
    }

    @Override
    public Page<DepartmentDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(departmentMapper::toDto);
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public Optional<DepartmentDto> findOne(Long id) {
        return findById(id).map(departmentMapper::toDto);
    }

    @Override
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public DepartmentDto create(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        Optional<Company> company = companyService.findById(departmentDto.getCompanyDto().getId());
        department.setCompany(company.get());
        return departmentMapper.toDto(save(department));
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public boolean existById(Long id) {
        return departmentRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentDto update(Department department, DepartmentDto departmentDto) {
        department.setName(departmentDto.getName());
        Optional<Company> company = companyService.findById(departmentDto.getCompanyDto().getId());
        company.ifPresent(department::setCompany);
        return departmentMapper.toDto(save(department));
    }
}
