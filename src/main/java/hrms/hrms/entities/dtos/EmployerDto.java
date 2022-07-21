package hrms.hrms.entities.dtos;





import hrms.hrms.core.entities.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployerDto extends UserDto {

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


}
