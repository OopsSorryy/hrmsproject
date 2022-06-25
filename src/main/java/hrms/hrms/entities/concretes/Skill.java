package hrms.hrms.entities.concretes;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int skillId;

    @Column(name = "name")
    private String skillName;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "cvId")
    private Cv cv;

    public void addSkillToCv(Cv cv){
        this.cv=cv;
    }
}
