package uz.azizbek.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Address;
import uz.azizbek.payload.AddressDto;
import uz.azizbek.service.AddressService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<ResponseData<Page<AddressDto>>> getPageAddress(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseData.response(addressService.findAllDto(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<AddressDto>> getOne(@PathVariable Long id) {
        Optional<AddressDto> result = addressService.findOne(id);
        return result.map(ResponseData::response)
                .orElseGet(() -> ResponseData.response("Address does not exist id: " + id));
    }

    @PostMapping
    public ResponseEntity<ResponseData<AddressDto>> createAddress(@RequestBody @Valid AddressDto addressDto) {
        if (addressDto.getId() != null)
            return ResponseData.response("Address already exist");
        AddressDto result = addressService.saveDto(addressDto);
        return ResponseData.response(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {

        if (!addressService.existById(id))
            return ResponseData.response("Address not found id: " + id);

        addressService.delete(id);
        return ResponseData.response((Object) "Successfully deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<AddressDto>> update(@PathVariable Long id,
                                                           @RequestBody @Valid AddressDto addressDto) {
        Optional<Address> address = addressService.findById(id);
        if (address.isEmpty())
            return ResponseData.response("Address not found id: " + id);

        AddressDto result = addressService.update(address.get(), addressDto);

        return ResponseData.response(result);
    }
}
