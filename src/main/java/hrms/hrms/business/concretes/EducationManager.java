package hrms.hrms.business.concretes;


import hrms.hrms.business.abstracts.EducationService;
import hrms.hrms.core.utilities.results.*;
import hrms.hrms.dataAccess.abstracts.EducationDao;
import hrms.hrms.entities.concretes.Education;
import hrms.hrms.entities.dtos.EducationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationManager implements EducationService {

    private EducationDao educationDao;
    private ModelMapper modelMapper;

    @Autowired
    public EducationManager(EducationDao educationDao, ModelMapper modelMapper) {
        this.educationDao = educationDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<EducationDto>> getAll() {
        List<Education> educations = this.educationDao.findAll();
        List<EducationDto> dtos= educations.stream().map(education -> modelMapper.map(education,EducationDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<EducationDto>>(dtos,"Data listed");
    }

    @Override
    public DataResult<EducationDto> getByEducationId(int educationId) {
        if(educationDao.getByEducationId(educationId)!=null){
            Education education = this.educationDao.getByEducationId(educationId);
            return new SuccessDataResult<EducationDto>((modelMapper.map(education,EducationDto.class)),"Data listed");
        }
        return new ErrorDataResult<EducationDto>("Education Id doesn't exist");
    }

    @Override
    public Result add(EducationDto educationDto) {
        Education education = modelMapper.map(educationDto, Education.class);

        modelMapper.map(this.educationDao.save(education), EducationDto.class);
        return new SuccessResult("Education added");
    }

    @Override
    public Result delete(int educationId) {
        if(educationDao.getByEducationId(educationId)!=null){
            educationDao.deleteByEducationId(educationId);
            return new SuccessResult("Education deleted");
        }
        return new ErrorResult("Education Id doesn't exist");
    }
}
