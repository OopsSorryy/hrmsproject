package hrms.hrms.entities.dtos;



import hrms.hrms.core.entities.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerDto extends UserDto {

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;


    @Size(min = 11, max = 11)
    private String nationalityId;

    private Date birthDate;
}
