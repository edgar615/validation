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
@Constraint(validatedBy = AlphaNumberConstraintValidator.class)
public @interface AlphaNumberValidator {

  String message() default "只能包含数字和大小写字母";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
