package hrms.hrms.business.concretes;

import hrms.hrms.business.abstracts.ForeignLanguageService;
import hrms.hrms.core.utilities.results.*;
import hrms.hrms.dataAccess.abstracts.CvDao;
import hrms.hrms.dataAccess.abstracts.ForeignLanguageDao;
import hrms.hrms.entities.concretes.Cv;
import hrms.hrms.entities.concretes.ForeignLanguage;
import hrms.hrms.entities.dtos.ForeignLanguageDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForeignLanguageManager implements ForeignLanguageService {

    private ForeignLanguageDao foreignLanguageDao;
    private ModelMapper modelMapper;
    private CvDao cvDao;

    @Autowired
    public ForeignLanguageManager(ForeignLanguageDao foreignLanguageDao, ModelMapper modelMapper,CvDao cvDao) {
        this.foreignLanguageDao = foreignLanguageDao;
        this.modelMapper = modelMapper;
        this.cvDao=cvDao;
    }

    @Override
    public DataResult<List<ForeignLanguageDto>> getAll() {
        List<ForeignLanguage> foreignLanguages = this.foreignLanguageDao.findAll();
        List<ForeignLanguageDto> dtos= foreignLanguages.stream().map(foreignLanguage -> modelMapper.map(foreignLanguage,ForeignLanguageDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ForeignLanguageDto>>(dtos,"Data listed");
    }

    @Override
    public DataResult<ForeignLanguageDto> getByForeignLanguageId(int foreignLanguageId) {
        if(foreignLanguageDao.getByForeignLanguageId(foreignLanguageId)!=null){
            ForeignLanguage foreignLanguage = this.foreignLanguageDao.getByForeignLanguageId(foreignLanguageId);
            return new SuccessDataResult<ForeignLanguageDto>((modelMapper.map(foreignLanguage,ForeignLanguageDto.class)),"Data listed");
        }
        return new ErrorDataResult<ForeignLanguageDto>("ForeignLanguage Id doesn't exist");
    }

    @Override
    public Result add(ForeignLanguageDto foreignLanguageDto) {
        ForeignLanguage foreignLanguage = modelMapper.map(foreignLanguageDto, ForeignLanguage.class);

        modelMapper.map(this.foreignLanguageDao.save(foreignLanguage), ForeignLanguageDto.class);
        return new SuccessResult("ForeignLanguage added");
    }

    @Override
    public Result delete(int foreignLanguageId) {
        if(foreignLanguageDao.getByForeignLanguageId(foreignLanguageId)!=null){
            foreignLanguageDao.deleteByForeignLanguageId(foreignLanguageId);
            return new SuccessResult("ForeignLanguage deleted");
        }
        return new ErrorResult("ForeignLanguage Id doesn't exist");
    }

    @Override
    public Result addForeignLanguageToCv(int cvId, int foreignLanguageId) {
        Cv cv = cvDao.getByCvId(cvId);
        ForeignLanguage foreignLanguage = foreignLanguageDao.getByForeignLanguageId(foreignLanguageId);
        foreignLanguage.addForeignLanguageToCv(cv);
        cvDao.save(cv);
        return new SuccessResult("Cv added by Foreign Language.");
    }
}
