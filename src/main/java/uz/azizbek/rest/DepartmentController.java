package uz.azizbek.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Department;
import uz.azizbek.payload.DepartmentDto;
import uz.azizbek.service.DepartmentService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<ResponseData<Page<DepartmentDto>>> getAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseData.response(departmentService.findAllDto(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<DepartmentDto>> getOne(@PathVariable Long id){
        Optional<DepartmentDto> result = departmentService.findOne(id);
        return result.map(ResponseData::response).
                orElseGet(()-> ResponseData.response("Department not found id: " + id));
    }

    @PostMapping
    public ResponseEntity<ResponseData<DepartmentDto>> create(@RequestBody @Valid DepartmentDto departmentDto){
        if (departmentDto.getId() != null)
            return ResponseData.response("Department already exist");

        DepartmentDto result = departmentService.create(departmentDto);

        return ResponseData.response(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (!departmentService.existById(id))
            return ResponseData.response("Department does not exist id: " + id);

        departmentService.delete(id);

        return ResponseData.response( (Object)"Successfully deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<DepartmentDto>> update(@PathVariable Long id,
                                                              @RequestBody @Valid DepartmentDto departmentDto){
        Optional<Department> department = departmentService.findById(id);
        if (department.isEmpty())
            return ResponseData.response("Department does not exist id: " + id);

        DepartmentDto result = departmentService.update(department.get(), departmentDto);

        return ResponseData.response(result);
    }

}
