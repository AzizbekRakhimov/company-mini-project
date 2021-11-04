package uz.azizbek.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class WorkerDto {
    private Long id;
    @NotEmpty(message = "Name can not be empty")
    private String name;
    private String phoneNumber;
    private AddressDto addressDto;
    private DepartmentDto departmentDto;
}
