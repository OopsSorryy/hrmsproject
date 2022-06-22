package hrms.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hrms.hrms.core.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(name = "employerName",nullable = false,unique = true)
    private String employerName;

    @NotBlank
    @Column(name = "webSite",nullable = false,unique = true)
    private String webSite;

    @NotBlank
    @Column(name = "telephoneNumber",nullable = false,length = 11,unique = true)
    private String telephoneNumber;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "employer")
    private List<JobAdvertisement> jobAdvertisements;
}
