package hrms.hrms.business.abstracts;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.dtos.SkillDto;

import java.util.List;

public interface SkillService {

    DataResult<List<SkillDto>> getAll();
    DataResult<SkillDto> getBySkillId(int skillId);
    Result add(SkillDto skillDto);
    Result delete(int skillId);
}
