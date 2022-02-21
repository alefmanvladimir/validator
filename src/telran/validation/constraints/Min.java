package telran.validation.constraints;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target(FIELD)
/**
 * validation constraint
 * defining maximal value
 * of a number field
 */
public @interface Min {
    int value();
    String message() default "Min constraint violation";
}
