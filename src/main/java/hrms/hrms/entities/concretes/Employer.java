package hrms.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hrms.hrms.core.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employers")
public class Employer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employerId")
    private int employerId;

    @Column(name = "employerName",unique = true)
    private String employerName;

    @Column(name = "webSite",unique = true)
    private String webSite;

    @Column(name = "telephoneNumber",unique = true)
    private String telephoneNumber;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "employer")
    private List<JobAdvertisement> jobAdvertisements;
}
