package uz.azizbek.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CompanyDto {
    private Long id;
    @NotEmpty(message = "CorpName can not be empty")
    private String corpName;
    private String directorName;
    private AddressDto addressDto;
}
