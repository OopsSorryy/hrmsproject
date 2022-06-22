package hrms.hrms.business.concretes;

import hrms.hrms.business.abstracts.SkillService;
import hrms.hrms.core.utilities.results.*;
import hrms.hrms.dataAccess.abstracts.SkillDao;
import hrms.hrms.entities.concretes.Skill;
import hrms.hrms.entities.dtos.SkillDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillManager implements SkillService {

    private SkillDao skillDao;
    private ModelMapper modelMapper;

    @Autowired
    public SkillManager(SkillDao skillDao , ModelMapper modelMapper) {
        this.skillDao = skillDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<SkillDto>> getAll() {
        List<Skill> skills = this.skillDao.findAll();
        List<SkillDto> dtos= skills.stream().map(skill -> modelMapper.map(skill, SkillDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<SkillDto>>(dtos,"Data listed");
    }

    @Override
    public DataResult<SkillDto> getBySkillId(int skillId) {
        if(skillDao.getBySkillId(skillId)!=null){
            Skill skill = this.skillDao.getById(skillId);
            return new SuccessDataResult<SkillDto>((modelMapper.map(skill, SkillDto.class)),"Data listed");
        }
        return new ErrorDataResult<SkillDto>("ProgrammingLanguageAndTechnology Id doesn't exist");
    }

    @Override
    public Result add(SkillDto skillDto) {
        Skill skill = modelMapper.map(skillDto, Skill.class);

        modelMapper.map(this.skillDao.save(skill), SkillDto.class);
        return new SuccessResult("ProgrammingLanguageAndTechnology added");
    }

    @Override
    public Result delete(int programmingLanguageAndTechnologyId) {
        if(skillDao.getById(programmingLanguageAndTechnologyId)!=null){
            skillDao.deleteById(programmingLanguageAndTechnologyId);
            return new SuccessResult("ProgrammingLanguageAndTechnology deleted");
        }
        return new ErrorResult("ProgrammingLanguageAndTechnology Id doesn't exist");
    }
}
