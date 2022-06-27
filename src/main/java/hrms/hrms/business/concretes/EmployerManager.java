package hrms.hrms.business.concretes;

import hrms.hrms.business.abstracts.EmployerService;
import hrms.hrms.core.utilities.results.*;
import hrms.hrms.dataAccess.abstracts.EmployerDao;
import hrms.hrms.entities.concretes.Employer;
import hrms.hrms.entities.dtos.EmployerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployerManager implements EmployerService {

    private EmployerDao employerDao;
    private ModelMapper modelMapper;

    @Autowired
    public EmployerManager(EmployerDao employerDao, ModelMapper modelMapper) {
        this.employerDao = employerDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<EmployerDto>> getAll() {

        List<Employer> employers = this.employerDao.findAll();
        List<EmployerDto> dtos= employers.stream().map(employer -> modelMapper.map(employer,EmployerDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<EmployerDto>>(dtos,"Data Listed");
    }

    @Override
    public DataResult<EmployerDto> getByEmployerId(int employerId) {

        if(employerDao.getByEmployerId(employerId)!=null){
            Employer employer = this.employerDao.getByEmployerId(employerId);
            return new SuccessDataResult<EmployerDto>((modelMapper.map(employer,EmployerDto.class)),"Data listed");
        }
        return new ErrorDataResult<EmployerDto>("Employer Id doesn't exist");
    }

    @Override
    public Result add(EmployerDto employerDto) {
        Employer employer = modelMapper.map(employerDto, Employer.class);
        if((this.employerDao.getByEmployerName(employer.getEmployerName()) !=null)
                || (this.employerDao.getByTelephoneNumber(employer.getTelephoneNumber()) != null)
                || (this.employerDao.getByWebSite(employer.getWebSite()) != null)){
            return new ErrorResult(" Employer already exist ");
        }

        if(!(employerDto.getEmail().split("@")[1].equalsIgnoreCase(employerDto.getWebSite()))){
            return new ErrorResult("Employer website domain and email domain don't equal");
        }

        modelMapper.map(this.employerDao.save(employer), EmployerDto.class);
        return new SuccessResult("Employer added");
    }

    @Override
    public Result update(int employerId, EmployerDto employerDto) {
        Employer employer = this.employerDao.getByEmployerId(employerId);

        if(employer != null){
            employer.setEmployerName(employerDto.getEmployerName());
            employer.setTelephoneNumber(employerDto.getTelephoneNumber());
            employer.setWebSite(employerDto.getWebSite());
            employer.setEmail(employerDto.getEmail());
            employer.setPassword(employerDto.getPassword());
            if(this.employerDao.getByEmployerName(employerDto.getEmployerName()) != null){
                return new ErrorResult(" Employer name already used ");
            }
            else if(this.employerDao.getByWebSite(employerDto.getWebSite()) != null){
                return new ErrorResult(" Employer webSite already used ");
            }
            else if(this.employerDao.getByTelephoneNumber(employerDto.getTelephoneNumber()) != null){
                return new ErrorResult(" Employer telephone number already used ");
            }
            else if(this.employerDao.getByEmail(employerDto.getEmail()) != null){

                return new ErrorResult(" Employer email already used ");
            }
            else{
                if(!(employerDto.getEmail().split("@")[1].equalsIgnoreCase(employerDto.getWebSite()))){
                    return new ErrorResult("Employer website domain and email domain don't equal");
                }
                modelMapper.map(employerDao.save(employer), EmployerDto.class);
                return new SuccessResult("Employer Updated");
            }



        }
        return new ErrorResult("Employer Id doesn't exist");
    }

    @Override
    public Result delete(int employerId) {
        if(employerDao.getByEmployerId(employerId)!=null){
            employerDao.deleteById(employerId);
            return new SuccessResult("Employer deleted");
        }
        return new ErrorResult("Employer Id doesn't exist");

    }
}
