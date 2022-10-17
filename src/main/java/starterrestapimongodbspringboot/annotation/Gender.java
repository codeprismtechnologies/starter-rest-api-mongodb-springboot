package starterrestapimongodbspringboot.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class Gender implements ConstraintValidator<ValidateGender, String> {

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext context) {
        Set<String> genderType = Set.of("MALE", "FEMALE");
        return genderType.contains(gender.toUpperCase());
    }
}
