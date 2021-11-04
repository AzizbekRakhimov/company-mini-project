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
@Entity
@Audited
@Table(name = "address", uniqueConstraints = {@UniqueConstraint(columnNames = {"street", "homeNumber"})})
public class Address extends AbstractAuditEntity {

    @Transient
    private static final String  seqName = "address_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = seqName)
    @SequenceGenerator(sequenceName = seqName, name = seqName, allocationSize = 1, initialValue = 2)
    private Long id;

    private String street;

    private String homeNumber;
}
