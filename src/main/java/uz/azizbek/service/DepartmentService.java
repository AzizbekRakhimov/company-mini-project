package uz.azizbek.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.azizbek.model.Department;
import uz.azizbek.payload.DepartmentDto;

import java.util.Optional;

public interface DepartmentService {
    Page<DepartmentDto> findAllDto(Pageable pageable);

    Page<Department> findAll(Pageable pageable);

    Optional<DepartmentDto> findOne(Long id);

    Optional<Department> findById(Long id);

    DepartmentDto create(DepartmentDto departmentDto);

    Department save(Department department);

    boolean existById(Long id);

    void delete(Long id);

    DepartmentDto update(Department department, DepartmentDto departmentDto);
}
