package com.github.edgar615.validation.jsr303;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FixLengthConstraintValidator.class)
public @interface FixLengthValidator {

  String message() default "长度必须等于{format}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  int length();

}
