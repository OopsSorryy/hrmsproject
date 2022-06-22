package hrms.hrms.business.concretes;

import hrms.hrms.business.abstracts.CvService;
import hrms.hrms.core.utilities.results.*;
import hrms.hrms.dataAccess.abstracts.CvDao;
import hrms.hrms.dataAccess.abstracts.JobSeekerDao;
import hrms.hrms.entities.concretes.Cv;
import hrms.hrms.entities.concretes.Employer;
import hrms.hrms.entities.concretes.JobAdvertisement;
import hrms.hrms.entities.concretes.JobSeeker;
import hrms.hrms.entities.dtos.CvDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CvManager implements CvService {

    private CvDao cvDao;
    private JobSeekerDao jobSeekerDao;
    private ModelMapper modelMapper;

    @Autowired
    public CvManager(CvDao cvDao, ModelMapper modelMapper,JobSeekerDao jobSeekerDao) {
        this.cvDao = cvDao;
        this.modelMapper = modelMapper;
        this.jobSeekerDao = jobSeekerDao;
    }

    @Override
    public DataResult<List<CvDto>> getAll() {
        List<Cv> cvs = this.cvDao.findAll();
        List<CvDto> dtos= cvs.stream().map(cv -> modelMapper.map(cv,CvDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CvDto>>(dtos,"Data listed");
    }

    @Override
    public DataResult<CvDto> getByCvId(int cvId) {
        if(cvDao.getByCvId(cvId)!=null){
            Cv cv = this.cvDao.getByCvId(cvId);
            return new SuccessDataResult<CvDto>((modelMapper.map(cv,CvDto.class)),"Data listed");
        }
        return new ErrorDataResult<CvDto>("Cv Id doesn't exist");
    }

    @Override
    public Result add(CvDto cvDto) {
        Cv cv = modelMapper.map(cvDto, Cv.class);

        modelMapper.map(this.cvDao.save(cv), CvDto.class);
        return new SuccessResult("Cv added");
    }

    @Override
    public Result delete(int cvId) {
        if(cvDao.getByCvId(cvId)!=null){
            cvDao.deleteByCvId(cvId);
            return new SuccessResult("Cv deleted");
        }
        return new ErrorResult("Cv Id doesn't exist");
    }

    @Override
    public Result addCvInJobSeeker(int cvId, int jobSeekerId) {
        Cv cv = cvDao.getByCvId(cvId);
        JobSeeker jobSeeker = jobSeekerDao.getByJobSeekerId(jobSeekerId);
        cv.addJobSeeker(jobSeeker);
        jobSeekerDao.save(jobSeeker);
        return new SuccessResult("Cv added by Job Seeker.");
    }
}
