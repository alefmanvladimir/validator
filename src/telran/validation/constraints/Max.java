package telran.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
/**
 * validation constraint
 * defining maximal value
 * of a number field
 */
public @interface Max {
    int value();
    String message() default "Max constraint violation";
}
