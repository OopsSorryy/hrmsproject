package hrms.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {

    private String jobName;


    private String jobDescription;


    private String jobCity;


    private String jobSalary;


    private String jobOpenPosition;

    private Date deadlineDate;

    private Date releaseDate;
}
