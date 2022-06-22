package hrms.hrms.business.abstracts;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.CvDto;
import hrms.hrms.entities.dtos.EducationDto;

import java.util.List;

public interface EducationService {

    DataResult<List<EducationDto>> getAll();
    DataResult<EducationDto> getByEducationId(int educationId);
    Result add(EducationDto educationDto);
    Result delete(int educationId);
}
