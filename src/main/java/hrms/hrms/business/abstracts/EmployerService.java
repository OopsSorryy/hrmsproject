package hrms.hrms.business.abstracts;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.Employer;
import hrms.hrms.entities.dtos.EmployerDto;


import java.util.List;

public interface EmployerService {

    DataResult<List<EmployerDto>> getAll();
    DataResult<EmployerDto> getByEmployerId(int employerId);
    Result add(EmployerDto employerDto);
    Result update(int employerId, EmployerDto employerDto);
    Result delete(int employerId);
}
