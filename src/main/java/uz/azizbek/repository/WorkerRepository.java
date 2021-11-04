package uz.azizbek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import uz.azizbek.model.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long>, RevisionRepository<Worker, Long, Long> {
}
