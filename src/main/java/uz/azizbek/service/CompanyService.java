package uz.azizbek.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.azizbek.model.Company;
import uz.azizbek.payload.CompanyDto;

import java.util.Optional;

public interface CompanyService {
    Page<CompanyDto> findAllDto(Pageable pageable);

    Page<Company> findAll(Pageable pageable);

    Optional<CompanyDto> findOne(Long id);

    Optional<Company> findById(Long id);

    CompanyDto create(CompanyDto companyDto);

    Company save(Company company);

    void deleteById(Long id);

    CompanyDto update(Company company, CompanyDto companyDto);

    boolean existById(Long id);
}
