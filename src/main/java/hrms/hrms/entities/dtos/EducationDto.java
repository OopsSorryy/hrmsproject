package hrms.hrms.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {

    private String schoolName;


    private String department;



    private Date startDate;



    private Date graduateDate;
}
