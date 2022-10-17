package starterrestapimongodbspringboot.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author : Chandan Rai
 * @created : 12/10/2022, Wednesday 17:43
 * @organisation : Code prism Technologies Pvt Ltd
 **/

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = Gender.class)
public @interface ValidateGender {

    public String message() default "Invalid Gender input. It should be either MALE or FEMALE";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
