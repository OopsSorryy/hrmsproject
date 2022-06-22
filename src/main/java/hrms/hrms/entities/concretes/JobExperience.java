package hrms.hrms.entities.concretes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "employerName", nullable = false)
    private String employerName;

    @Column(name = "jobPosition", nullable = false)
    private String jobPosition;

    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "graduateDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToOne()
    @JoinColumn(name = "cvId")
    private Cv cv;
}
