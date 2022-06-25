package hrms.hrms.entities.concretes;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "jobExperiences")
public class JobExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobExperienceId")
    private int jobExperienceId;

    @Column(name = "employerName")
    private String employerName;

    @Column(name = "jobPosition")
    private String jobPosition;

    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "cvId")
    private Cv cv;

    public void addJobExperienceToCv(Cv cv){
        this.cv=cv;
    }
}
