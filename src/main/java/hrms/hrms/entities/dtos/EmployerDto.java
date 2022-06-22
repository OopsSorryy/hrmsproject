package hrms.hrms.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDto {

    private String webSite;

    private String employerName;

    private String telephoneNumber;

    private String email;

    private String password;
}
