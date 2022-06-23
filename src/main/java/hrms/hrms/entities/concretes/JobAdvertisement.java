package hrms.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jobAdvertisements")
public class JobAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobId")
    private int jobId;

    @Column(name = "jobName")
    private String jobName;


    @Column(name = "jobDescription")
    private String jobDescription;


    @Column(name = "jobCity")
    private String jobCity;


    @Column(name = "jobSalary")
    private String jobSalary;


    @Column(name = "jobOpenPosition")
    private String jobOpenPosition;


    @Column(name = "releaseDate")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;


    @Column(name = "deadlineDate")
    @Temporal(TemporalType.DATE)
    private Date deadlineDate;

    @ManyToOne()
    @JoinColumn(name = "employerId")
    private Employer employer;

    public void addEmployer(Employer employer){
        this.employer=employer;
    }
}
