package hrms.hrms.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobExperienceDto {

    @NotNull
    @NotBlank
    private String employerName;

    @NotNull
    @NotBlank
    private String jobPosition;

    private Date startDate;

    private Date endDate;
}
