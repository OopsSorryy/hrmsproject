package hrms.hrms.business.concretes;

import hrms.hrms.business.abstracts.JobSeekerService;
import hrms.hrms.core.utilities.results.*;
import hrms.hrms.dataAccess.abstracts.JobSeekerDao;
import hrms.hrms.entities.concretes.JobSeeker;
import hrms.hrms.entities.dtos.JobSeekerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobSeekerManager implements JobSeekerService {

    private JobSeekerDao jobSeekerDao;
    private ModelMapper modelMapper;


    @Autowired
    public JobSeekerManager(JobSeekerDao jobSeekerDao, ModelMapper modelMapper) {
        this.jobSeekerDao = jobSeekerDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<JobSeekerDto>> getAll() {
        List<JobSeeker> jobSeekers = this.jobSeekerDao.findAll();
        List<JobSeekerDto> dtos= jobSeekers.stream().map(jobSeeker -> modelMapper.map(jobSeeker,JobSeekerDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<JobSeekerDto>>(dtos,"Data listed");
    }

    @Override
    public DataResult<JobSeekerDto> getByJobSeekerId(int jobSeekerId) {

        if(jobSeekerDao.getByJobSeekerId(jobSeekerId)!=null){
            JobSeeker jobSeeker = this.jobSeekerDao.getByJobSeekerId(jobSeekerId);
            return new SuccessDataResult<JobSeekerDto>((modelMapper.map(jobSeeker,JobSeekerDto.class)),"Data listed");
        }
        return new ErrorDataResult<JobSeekerDto>("JobSeeker Id doesn't exist");
    }

    @Override
    public Result add(JobSeekerDto jobSeekerDto) {
        JobSeeker jobSeeker = modelMapper.map(jobSeekerDto, JobSeeker.class);
        if((this.jobSeekerDao.getByNationalityId(jobSeeker.getNationalityId()) !=null) || (this.jobSeekerDao.getByEmail(jobSeeker.getEmail()) != null) ){
            return new ErrorResult(" JobSeeker already exist ");
        }


        modelMapper.map(this.jobSeekerDao.save(jobSeeker), JobSeekerDto.class);
        return new SuccessResult("JobSeeker added");

    }

    @Override
    public Result update(int jobSeekerId, JobSeekerDto jobSeekerDto) {
        JobSeeker jobSeeker = this.jobSeekerDao.getByJobSeekerId(jobSeekerId);

        if(jobSeeker != null){
            jobSeeker.setEmail(jobSeekerDto.getEmail());
            jobSeeker.setPassword(jobSeekerDto.getPassword());
            jobSeeker.setBirthDate(jobSeekerDto.getBirthDate());
            jobSeeker.setFirstName(jobSeekerDto.getFirstName());
            jobSeeker.setLastName(jobSeekerDto.getLastName());

            if(this.jobSeekerDao.getByEmail(jobSeekerDto.getEmail()) != null){
                return new ErrorResult(" JobSeeker e mail already used ");
            }
            modelMapper.map(jobSeekerDao.save(jobSeeker), JobSeekerDto.class);
            return new SuccessResult("Job Seeker Updated");

        }
        return new ErrorResult("Job Seeker Id doesn't exist");
    }

    @Override
    public Result delete(int jobSeekerId) {
        if(jobSeekerDao.getByJobSeekerId(jobSeekerId)!=null){
            jobSeekerDao.deleteById(jobSeekerId);
            return new SuccessResult("JobSeeker deleted");
        }
        return new ErrorResult("JobSeeker Id doesn't exist");

    }
}
