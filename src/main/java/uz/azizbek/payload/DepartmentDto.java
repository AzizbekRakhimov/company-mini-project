package uz.azizbek.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DepartmentDto {
    private Long id;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    private CompanyDto companyDto;
}
