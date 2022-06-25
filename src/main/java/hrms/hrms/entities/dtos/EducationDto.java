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
public class EducationDto {

    @NotNull
    @NotBlank
    private String schoolName;

    @NotNull
    @NotBlank
    private String department;

    private Date startDate;

    private Date graduateDate;
}
