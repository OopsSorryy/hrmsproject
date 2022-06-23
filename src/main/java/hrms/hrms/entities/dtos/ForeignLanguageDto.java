package hrms.hrms.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForeignLanguageDto {

    @NotNull
    @NotBlank
    private String foreignLanguageName;

    @Max(5)
    @Min(1)
    private int foreignLanguageLevel;
}
