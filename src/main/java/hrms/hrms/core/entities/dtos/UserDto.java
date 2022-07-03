package hrms.hrms.core.entities.dtos;

import hrms.hrms.core.utilities.validator.PasswordMatches;
import hrms.hrms.core.utilities.validator.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class UserDto {

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
