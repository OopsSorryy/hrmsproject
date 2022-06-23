package hrms.hrms.entities.concretes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foreignLanguages")
public class ForeignLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foreignLanguageId")
    private int foreignLanguageId;


    @Column(name = "foreignLanguageName")
    private String foreignLanguageName;


    @Column(name = "foreignLanguageLevel")
    private int foreignLanguageLevel;

    @ManyToOne()
    @JoinColumn(name = "cvId")
    private Cv cv;
}
