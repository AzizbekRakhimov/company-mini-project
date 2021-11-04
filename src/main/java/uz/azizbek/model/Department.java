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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "company_id"}))
public class Department extends AbstractAuditEntity {

    @Transient
    private static final String seqName = "department_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = seqName)
    @SequenceGenerator(name = seqName, sequenceName = seqName, allocationSize = 1, initialValue = 101)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
}
