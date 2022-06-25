package hrms.hrms.core.utilities.validator;

import hrms.hrms.entities.dtos.EmployerDto;
import hrms.hrms.entities.dtos.JobSeekerDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EPasswordMatchesValidator implements ConstraintValidator<EPasswordMatches, Object> {

    @Override
    public void initialize(EPasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        EmployerDto employer = ( EmployerDto) obj;
        return employer.getPassword().equals(employer.getMatchingPassword());
    }
}
