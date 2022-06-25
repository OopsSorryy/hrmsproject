//package hrms.hrms.core.utilities.validator;
//
//import hrms.hrms.entities.dtos.ForeignLanguageDto;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class PasswordMatchesValidator
//        implements ConstraintValidator<PasswordMatches, Object> {
//
//    @Override
//    public void initialize(PasswordMatches constraintAnnotation) {
//    }
//    @Override
//    public boolean isValid(Object obj, ConstraintValidatorContext context){
//        ForeignLanguageDto foreignLanguageDto = (ForeignLanguageDto) obj;
//        return foreignLanguageDto.getPassword().equals(foreignLanguageDto.getConfirmPassword());
//    }
//}