package uz.azizbek.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.Audited;
import uz.azizbek.audit.AbstractAuditEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Audited
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "phoneNumber", "address_id", "department_id"}))
public class Worker extends AbstractAuditEntity {
    @Transient
    private static final String seqName = "worker_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = seqName)
    @SequenceGenerator(name = seqName, sequenceName = seqName, allocationSize = 1, initialValue = 1001)
    private Long id;

    private String name;

    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Department department;
}
