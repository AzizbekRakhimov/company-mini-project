package uz.azizbek.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.azizbek.model.Worker;
import uz.azizbek.payload.WorkerDto;

import java.util.Optional;

public interface WorkerService {
    Page<WorkerDto> findAllDto(Pageable pageable);

    Page<Worker> findAll(Pageable pageable);

    boolean existById(Long id);

    Optional<WorkerDto> findOne(Long id);

    Optional<Worker> findById(Long id);

    WorkerDto create(WorkerDto workerDto);

    Worker save(Worker worker);

    WorkerDto update(Worker worker, WorkerDto workerDto);

    void delete(Long id);
}
