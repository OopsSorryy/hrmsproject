package hrms.hrms.core.utilities.validator;

import hrms.hrms.entities.dtos.JobSeekerDto;
import hrms.hrms.entities.dtos.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserDto user = ( UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}