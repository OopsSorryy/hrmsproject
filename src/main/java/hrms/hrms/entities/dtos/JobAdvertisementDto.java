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
public class JobAdvertisementDto {

    @NotNull
    @NotBlank
    private String jobName;

    @NotNull
    @NotBlank
    private String jobDescription;

    @NotNull
    @NotBlank
    private String jobCity;

    @NotNull
    @NotBlank
    private String jobSalary;

    @NotNull
    @NotBlank
    private String jobOpenPosition;

    private Date deadlineDate;

    private Date releaseDate;
}
