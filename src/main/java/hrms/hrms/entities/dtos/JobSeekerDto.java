package hrms.hrms.entities.dtos;


import hrms.hrms.core.utilities.validator.JPasswordMatches;
import hrms.hrms.core.utilities.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JPasswordMatches
public class JobSeekerDto {

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    @Email(regexp = ".+@.+\\..+")
    private String email;

    @NotNull
    @NotBlank
    @ValidPassword
    private String password;

    @NotNull
    @NotBlank
    private String matchingPassword;

    @Size(min = 11, max = 11)
    private String nationalityId;

    private Date birthDate;
}
