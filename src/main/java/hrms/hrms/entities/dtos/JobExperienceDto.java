package hrms.hrms.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobExperienceDto {

    private String employerName;


    private String jobPosition;


    private Date startDate;


    private Date endDate;
}
