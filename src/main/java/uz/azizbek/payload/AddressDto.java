package uz.azizbek.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddressDto {
    private Long id;
    @NotEmpty(message = "Street can not be empty")
    private String street;
    @NotEmpty(message = "homeNumber can not be empty")
    private String homeNumber;
}
