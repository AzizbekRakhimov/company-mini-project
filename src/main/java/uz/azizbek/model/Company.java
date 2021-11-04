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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"corpName", "directorName", "address_id"}))
public class Company extends AbstractAuditEntity {

    @Transient
    private static final String seqName = "company_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = seqName)
    @SequenceGenerator(name = seqName, sequenceName = seqName, allocationSize = 1, initialValue = 2)
    private Long id;

    private String corpName;

    private String directorName;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
