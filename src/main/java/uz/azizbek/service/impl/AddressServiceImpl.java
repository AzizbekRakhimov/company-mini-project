package uz.azizbek.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.azizbek.model.Address;
import uz.azizbek.payload.AddressDto;
import uz.azizbek.repository.AddressRepository;
import uz.azizbek.service.AddressService;
import uz.azizbek.service.mapper.AddressMapper;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public Page<AddressDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(addressMapper::toDto);
    }

    @Override
    public Page<Address> findAll(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    @Override
    public Optional<AddressDto> findOne(Long id) {
        return findById(id).map(addressMapper::toDto);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public AddressDto saveDto(AddressDto addressDto) {
        Address address = save(addressMapper.toEntity(addressDto));
        return addressMapper.toDto(address);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public AddressDto update(Address address, AddressDto addressDto) {
        Address result = addressMapper.toEntity(addressDto);
        result.setId(address.getId());
        return addressMapper.toDto(save(result));
    }

    @Override
    public boolean existById(Long id) {
        return addressRepository.existsById(id);
    }
}
