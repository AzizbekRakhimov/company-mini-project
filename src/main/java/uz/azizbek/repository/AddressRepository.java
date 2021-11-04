package uz.azizbek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import uz.azizbek.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, RevisionRepository<Address, Long, Long> {
}
