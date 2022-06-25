package hrms.hrms.business.abstracts;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.CvAddDto;
import hrms.hrms.entities.dtos.CvDto;


import java.util.List;

public interface CvService {

    DataResult<List<CvDto>> getAll();
    DataResult<CvDto> getByCvId(int cvId);
    Result add(CvAddDto cvAddDto);
    Result delete(int cvId);

    Result addCvInJobSeeker(int cvId,int jobSeekerId );
}
