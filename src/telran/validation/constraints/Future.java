package telran.validation.constraints;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
/**
 * field may be only LocalDate greater than the current date
 */
public @interface Future {
    String message() default "field may be a date of future date";
}
