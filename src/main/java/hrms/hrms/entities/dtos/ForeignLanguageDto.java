package hrms.hrms.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForeignLanguageDto {

    private String foreignLanguageName;

    private int foreignLanguageLevel;
}
