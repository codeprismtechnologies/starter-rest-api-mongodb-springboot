package starterrestapimongodbspringboot.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

/**
 * @author : Chandan Rai
 * @created : 12/10/2022, Wednesday 17:43
 * @organisation : Code prism Technologies Pvt Ltd
 **/

public class Gender implements ConstraintValidator<ValidateGender, String> {

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext context) {
        Set<String> genderType = Set.of("MALE", "FEMALE");
        return genderType.contains(gender.toUpperCase());
    }
}
