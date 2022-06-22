package hrms.hrms.business.concretes;

import hrms.hrms.business.abstracts.JobAdvertisementService;
import hrms.hrms.core.utilities.results.*;
import hrms.hrms.dataAccess.abstracts.EmployerDao;
import hrms.hrms.dataAccess.abstracts.JobAdvertisementDao;
import hrms.hrms.entities.concretes.Employer;
import hrms.hrms.entities.concretes.JobAdvertisement;
import hrms.hrms.entities.dtos.JobAdvertisementDto;
import hrms.hrms.entities.dtos.JobAdvertisementWithEmployerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {
    private JobAdvertisementDao jobAdvertisementDao;
    private ModelMapper modelMapper;
    private EmployerDao employerDao;

    @Autowired
    public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, ModelMapper modelMapper,EmployerDao employerDao) {
        this.jobAdvertisementDao = jobAdvertisementDao;
        this.modelMapper = modelMapper;
        this.employerDao=employerDao;
    }

    @Override
    public DataResult<List<JobAdvertisementDto>> getAll() {
        List<JobAdvertisement> jobAdvertisements = this.jobAdvertisementDao.findAll();
        List<JobAdvertisementDto> dtos= jobAdvertisements.stream().map(jobAdvertisement -> modelMapper.map(jobAdvertisement, JobAdvertisementDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<JobAdvertisementDto>>(dtos,"data listed");
    }

    @Override
    public DataResult<List<JobAdvertisementWithEmployerDto>> getJobAdvertisementWithEmployerName() {
        return new SuccessDataResult<List<JobAdvertisementWithEmployerDto>>
                (this.jobAdvertisementDao.getJobAdvertisementWithEmployerName(),"data listed");
    }

    @Override
    public DataResult<JobAdvertisementDto> getByJobId(int jobId) {

        if(jobAdvertisementDao.getByJobId(jobId)!=null){
            JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.getByJobId(jobId);
            return new SuccessDataResult<JobAdvertisementDto>((modelMapper.map(jobAdvertisement, JobAdvertisementDto.class)),"data listed");
        }
        return new ErrorDataResult<JobAdvertisementDto>("job Id doesn't exist");
    }
    @Modifying
    @Override
    public Result add(JobAdvertisementDto jobAdvertisementDto) {
        JobAdvertisement jobAdvertisement = modelMapper.map(jobAdvertisementDto, JobAdvertisement.class);
        modelMapper.map(this.jobAdvertisementDao.save(jobAdvertisement), JobAdvertisementDto.class);
        return new SuccessResult("Job added");
    }
    @Modifying
    @Override
    public Result addJobAdvertisementInEmployer(int employerId, int jobId){
        JobAdvertisement jobAdvertisement = jobAdvertisementDao.getByJobId(jobId);
        Employer employers = employerDao.getByEmployerId(employerId);
        jobAdvertisement.addEmployer(employers);
        employerDao.save(employers);
        return new SuccessResult("Job Advertisement added by employer.");
    }

    @Modifying
    @Override
    public Result delete(int jobId) {
        if(jobAdvertisementDao.getByJobId(jobId)!=null){
            jobAdvertisementDao.deleteByJobId(jobId);
            return new SuccessResult("Job deleted");
        }
        return new ErrorResult("Job Id doesn't exist");

    }
    @Override
    public DataResult<List<JobAdvertisement>> getAllSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC,"deadlineDate");
        return new SuccessDataResult<List<JobAdvertisement>>
                (this.jobAdvertisementDao.findAll(sort),"Başarılı");
    }
}
