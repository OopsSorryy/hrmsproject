package hrms.hrms.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Long nationalityId;

    private Date birthDate;
}
