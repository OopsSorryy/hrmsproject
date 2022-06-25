package hrms.hrms.business.concretes;

import hrms.hrms.business.abstracts.SkillService;
import hrms.hrms.core.utilities.results.*;
import hrms.hrms.dataAccess.abstracts.CvDao;
import hrms.hrms.dataAccess.abstracts.SkillDao;
import hrms.hrms.entities.concretes.Cv;
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

    private CvDao cvDao;

    @Autowired
    public SkillManager(SkillDao skillDao , ModelMapper modelMapper,CvDao cvDao) {
        this.skillDao = skillDao;
        this.modelMapper = modelMapper;
        this.cvDao = cvDao;
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
        return new ErrorDataResult<SkillDto>("Skill Id doesn't exist");
    }

    @Override
    public Result add(SkillDto skillDto) {
        Skill skill = modelMapper.map(skillDto, Skill.class);

        modelMapper.map(this.skillDao.save(skill), SkillDto.class);
        return new SuccessResult("Skill added");
    }

    @Override
    public Result delete(int skillId) {
        if(skillDao.getBySkillId(skillId)!=null){
            skillDao.deleteById(skillId);
            return new SuccessResult("Skill deleted");
        }
        return new ErrorResult("Skill Id doesn't exist");
    }

    @Override
    public Result addSkillToCv(int cvId, int skillId) {
        Cv cv = cvDao.getByCvId(cvId);
        Skill skill = skillDao.getById(skillId);
        skill.addSkillToCv(cv);
        cvDao.save(cv);
        return new SuccessResult("Cv added by Skill.");
    }
}
