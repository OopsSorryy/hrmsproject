package hrms.hrms.entities.dtos;


import hrms.hrms.core.utilities.validator.EPasswordMatches;
import hrms.hrms.core.utilities.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EPasswordMatches
public class EmployerDto {

    @NotNull
    @NotBlank
    private String webSite;

    @NotNull
    @NotBlank
    private String employerName;

    @NotNull
    @NotBlank
    @Size(min = 11, max = 11)
    private String telephoneNumber;

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
}
