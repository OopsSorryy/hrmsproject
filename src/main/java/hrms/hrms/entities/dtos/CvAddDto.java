package hrms.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvAddDto {

    private String photo;

    private String githubLink;

    private String linkedInLink;

    private String description;
}
