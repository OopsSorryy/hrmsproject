package hrms.hrms.core.utilities.validator;

import hrms.hrms.entities.dtos.JobSeekerDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JPasswordMatchesValidator implements ConstraintValidator<JPasswordMatches, Object> {

    @Override
    public void initialize(JPasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        JobSeekerDto jobSeeker = ( JobSeekerDto) obj;
        return jobSeeker.getPassword().equals(jobSeeker.getMatchingPassword());
    }
}