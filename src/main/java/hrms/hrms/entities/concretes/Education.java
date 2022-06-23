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
@Table(name = "educations")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "educationId")
    private int educationId;

    @Column(name = "schoolName")
    private String schoolName;

    @Column(name = "department")
    private String department;

    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "graduateDate")
    @Temporal(TemporalType.DATE)
    private Date graduateDate;

    @ManyToOne()
    @JoinColumn(name = "cvId")
    private Cv cv;


}
