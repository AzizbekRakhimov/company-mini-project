package uz.azizbek.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.azizbek.model.Address;
import uz.azizbek.payload.AddressDto;

import java.util.Optional;

public interface AddressService {
    Page<AddressDto> findAllDto(Pageable pageable);

    Page<Address> findAll(Pageable pageable);

    Optional<AddressDto> findOne(Long id);

    Optional<Address> findById(Long id);

    AddressDto saveDto(AddressDto addressDto);

    Address save(Address address);

    void delete(Long id);

    AddressDto update(Address address, AddressDto addressDto);

    boolean existById(Long id);
}
