package hrms.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hrms.hrms.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "employerId",referencedColumnName = "userId")
public class Employer extends User {

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
