package hrms.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cvs")
public class Cv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cvId")
    private int cvId;

    @Column(name = "photo")
    private String photo;

    @Column(name = "githubLink")
    private String githubLink;

    @Column(name = "linkedInLink")
    private String linkedInLink;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cv")
    private List<Education> educations;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cv")
    private List<ForeignLanguage> foreignLanguages;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cv")
    private List<JobExperience> jobExperiences;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cv")
    private List<Skill> skills;

    @ManyToOne()
    @JoinColumn(name = "jobSeekerId")
    private JobSeeker jobSeeker;

    public void addJobSeeker(JobSeeker jobSeeker){
        this.jobSeeker=jobSeeker;
    }
}
