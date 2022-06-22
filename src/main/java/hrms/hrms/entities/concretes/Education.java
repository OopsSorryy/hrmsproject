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
@Table(name = "educations")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "educationId")
    private int educationId;

    @Column(name = "schoolName", nullable = false)
    private String schoolName;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "graduateDate")
    @Temporal(TemporalType.DATE)
    private Date graduateDate;

    @ManyToOne()
    @JoinColumn(name = "cvId")
    private Cv cv;


}
