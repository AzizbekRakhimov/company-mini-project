package uz.azizbek.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.azizbek.model.Address;
import uz.azizbek.model.Company;
import uz.azizbek.payload.CompanyDto;
import uz.azizbek.repository.CompanyRepository;
import uz.azizbek.service.AddressService;
import uz.azizbek.service.CompanyService;
import uz.azizbek.service.mapper.CompanyMapper;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final AddressService addressService;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper, AddressService addressService) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.addressService = addressService;
    }


    @Override
    public Page<CompanyDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(companyMapper::toDto);
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public Optional<CompanyDto> findOne(Long id) {
        return findById(id).map(companyMapper::toDto);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public CompanyDto create(CompanyDto companyDto) {
        Company result = new Company();
        Optional<Address> address = addressService.findById(companyDto.getAddressDto().getId());
        result.setAddress(address.get());
        result.setCorpName(companyDto.getCorpName());
        result.setDirectorName(companyDto.getDirectorName());
        return companyMapper.toDto(save(result));
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyDto update(Company company, CompanyDto companyDto) {
        Company result = new Company();
        Optional<Address> address = addressService.findById(companyDto.getAddressDto().getId());
        result.setAddress(address.get());
        result.setCorpName(companyDto.getCorpName());
        result.setDirectorName(companyDto.getDirectorName());
        result.setId(company.getId());
        return companyMapper.toDto(save(result));
    }

    @Override
    public boolean existById(Long id) {
        return companyRepository.existsById(id);
    }
}
