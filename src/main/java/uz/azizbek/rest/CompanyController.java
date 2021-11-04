package uz.azizbek.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Company;
import uz.azizbek.payload.CompanyDto;
import uz.azizbek.service.CompanyService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<ResponseData<Page<CompanyDto>>> getAllDto(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseData.response(companyService.findAllDto(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<CompanyDto>> getOne(@PathVariable Long id) {
        Optional<CompanyDto> result = companyService.findOne(id);
        return result.map(ResponseData::response)
                .orElseGet(() -> ResponseData.response("Company does not exist id: " + id));
    }

    @PostMapping
    public ResponseEntity<ResponseData<CompanyDto>> createCompany(@RequestBody @Valid CompanyDto companyDto) {
        if (companyDto.getId() != null)
            return ResponseData.response("Company already exist");

        return ResponseData.response(companyService.create(companyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        if (!companyService.existById(id))
            return ResponseData.response("Company not found id: " + id);

        companyService.deleteById(id);
        return ResponseData.response((Object)"Successfully deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<CompanyDto>> update(@PathVariable Long id,
                                                           @RequestBody CompanyDto companyDto) {
        Optional<Company> company = companyService.findById(id);
        if (company.isEmpty())
            return ResponseData.response("Company does not exist id: " + id);

        CompanyDto result = companyService.update(company.get(), companyDto);

        return ResponseData.response(result);
    }
}
