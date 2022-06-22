package hrms.hrms.business.abstracts;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.JobAdvertisement;
import hrms.hrms.entities.dtos.JobAdvertisementDto;
import hrms.hrms.entities.dtos.JobAdvertisementWithEmployerDto;

import java.util.List;

public interface JobAdvertisementService {
    DataResult<List<JobAdvertisementDto>> getAll();
    DataResult<List<JobAdvertisementWithEmployerDto>> getJobAdvertisementWithEmployerName();
    DataResult<JobAdvertisementDto> getByJobId(int jobId);
    Result add(JobAdvertisementDto jobAdvertisementDto);
    Result addJobAdvertisementInEmployer(int employerId,int jobId );
    Result delete(int jobId);

    DataResult<List<JobAdvertisement>> getAllSorted();
}
