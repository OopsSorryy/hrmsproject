package hrms.hrms.business.abstracts;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.JobPositionDto;


import java.util.List;

public interface JobPositionService {
    DataResult<List<JobPositionDto>> getAll();
    DataResult<JobPositionDto> getByJobPositionId(int jobPositionId);
    Result add(JobPositionDto jobPositionDto);
    Result update(int jobPositionId, JobPositionDto jobPositionDto);
    Result delete(int jobPositionId);
}
