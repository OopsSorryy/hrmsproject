package hrms.hrms.business.concretes;

import hrms.hrms.business.abstracts.JobExperienceService;
import hrms.hrms.core.utilities.results.*;
import hrms.hrms.dataAccess.abstracts.JobExperienceDao;
import hrms.hrms.entities.concretes.JobExperience;
import hrms.hrms.entities.dtos.JobExperienceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobExperienceManager implements JobExperienceService {

    private JobExperienceDao jobExperienceDao;
    private ModelMapper modelMapper;

    @Autowired
    public JobExperienceManager(JobExperienceDao jobExperienceDao, ModelMapper modelMapper) {
        this.jobExperienceDao = jobExperienceDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<JobExperienceDto>> getAll() {
        List<JobExperience> jobExperiences = this.jobExperienceDao.findAll();
        List<JobExperienceDto> dtos= jobExperiences.stream().map(jobExperience -> modelMapper.map(jobExperience,JobExperienceDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<JobExperienceDto>>(dtos,"Data listed");
    }

    @Override
    public DataResult<JobExperienceDto> getByJobExperienceId(int jobExperienceId) {
        if(jobExperienceDao.getByJobExperienceId(jobExperienceId)!=null){
            JobExperience jobExperience = this.jobExperienceDao.getByJobExperienceId(jobExperienceId);
            return new SuccessDataResult<JobExperienceDto>((modelMapper.map(jobExperience,JobExperienceDto.class)),"Data listed");
        }
        return new ErrorDataResult<JobExperienceDto>("JobExperience Id doesn't exist");
    }

    @Override
    public Result add(JobExperienceDto jobExperienceDto) {
        JobExperience jobExperience = modelMapper.map(jobExperienceDto, JobExperience.class);

        modelMapper.map(this.jobExperienceDao.save(jobExperience), JobExperienceDto.class);
        return new SuccessResult("JobExperience added");
    }

    @Override
    public Result delete(int jobExperienceId) {
        if(jobExperienceDao.getByJobExperienceId(jobExperienceId)!=null){
            jobExperienceDao.deleteByJobExperienceId(jobExperienceId);
            return new SuccessResult("JobExperience deleted");
        }
        return new ErrorResult("JobExperience Id doesn't exist");
    }
}
