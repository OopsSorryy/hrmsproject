package hrms.hrms.business.abstracts;


import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.EducationDto;
import hrms.hrms.entities.dtos.ForeignLanguageDto;

import java.util.List;

public interface ForeignLanguageService {
    DataResult<List<ForeignLanguageDto>> getAll();
    DataResult<ForeignLanguageDto> getByForeignLanguageId(int foreignLanguageId);
    Result add(ForeignLanguageDto foreignLanguageDto);
    Result delete(int foreignLanguageId);

}
