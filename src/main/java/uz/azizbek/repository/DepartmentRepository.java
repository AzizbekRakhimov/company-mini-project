package uz.azizbek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import uz.azizbek.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, RevisionRepository<Department, Long, Long> {
}
