package uz.azizbek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import uz.azizbek.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, RevisionRepository<Company, Long, Long> {
}
