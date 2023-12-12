package com.ra.model.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileNotNullValidator.class})
public @interface FileNotNull {
    String message() default "Invalid image file";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
