package hrms.hrms.business.abstracts;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.ForeignLanguageDto;
import hrms.hrms.entities.dtos.JobExperienceDto;

import java.util.List;

public interface JobExperienceService {

    DataResult<List<JobExperienceDto>> getAll();
    DataResult<JobExperienceDto> getByJobExperienceId(int jobExperienceId);
    Result add(JobExperienceDto jobExperienceDto);
    Result delete(int jobExperienceId);

    Result addJobExperienceToCv(int cvId,int JobExperienceId );
    DataResult<List<JobExperienceDto>> getAllSorted();
}
