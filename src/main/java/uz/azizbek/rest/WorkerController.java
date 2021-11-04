package uz.azizbek.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Worker;
import uz.azizbek.payload.WorkerDto;
import uz.azizbek.service.WorkerService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public ResponseEntity<ResponseData<Page<WorkerDto>>> getAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseData.response(workerService.findAllDto(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<WorkerDto>> getOne(@PathVariable Long id) {
        Optional<WorkerDto> result = workerService.findOne(id);

        return result.map(ResponseData::response)
                .orElseGet(() -> ResponseData.response("Worker does not exist id: " + id));
    }

    @PostMapping
    public ResponseEntity<ResponseData<WorkerDto>> create(@RequestBody @Valid WorkerDto workerDto){
        if (workerDto.getId() != null)
            return ResponseData.response("Worker already exist");

        return ResponseData.response(workerService.create(workerDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<WorkerDto>> update(@PathVariable Long id,
                                                          @RequestBody @Valid WorkerDto workerDto){
        Optional<Worker> worker = workerService.findById(id);
        if (worker.isEmpty())
            return ResponseData.response("Worker does not exist id: " + id);

        WorkerDto result = workerService.update(worker.get(), workerDto);

        return ResponseData.response(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (!workerService.existById(id))
            return ResponseData.response("Worker does not exist");

        workerService.delete(id);
        return ResponseData.response((Object) "Successfully deleted");
    }
}
