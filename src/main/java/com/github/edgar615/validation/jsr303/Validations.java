package com.github.edgar615.validation.jsr303;

import com.github.edgar615.validation.ValidationException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.hibernate.validator.HibernateValidator;

/**
 * 校验工具类
 */
public class Validations {

  /**
   * 开启快速结束模式 failFast (true)
   */
  private static Validator validator = Validation.byProvider(HibernateValidator.class).configure()
      .failFast(false).buildValidatorFactory().getValidator();

  /**
   * 校验对象
   *
   * @param t bean
   * @param groups 校验组
   */
  public static <T> void validateBean(T t, Class<?>... groups) {
    Set<ConstraintViolation<T>> violationSet = validator.validate(t, groups);
    boolean hasError = violationSet != null && violationSet.size() > 0;
    if (hasError) {
      ValidationException validationException = new ValidationException();
      for (ConstraintViolation<T> violation : violationSet) {
        validationException
            .putError(violation.getPropertyPath().toString(), violation.getMessage());
      }
      throw validationException;
    }
  }

  /**
   * 校验bean的某一个属性
   *
   * @param obj bean
   * @param propertyName 属性名称
   */
  public static <T> void validateProperty(T obj, String propertyName) {
    Set<ConstraintViolation<T>> violationSet = validator.validateProperty(obj, propertyName);
    boolean hasError = violationSet != null && violationSet.size() > 0;
    if (hasError) {
      ValidationException validationException = transformEx(violationSet);
      throw validationException;
    }
  }

  private static <T> ValidationException transformEx(Set<ConstraintViolation<T>> violationSet) {
    ValidationException validationException = new ValidationException();
    for (ConstraintViolation<T> violation : violationSet) {
      validationException
          .putError(violation.getPropertyPath().toString(), violation.getMessage());
    }
    return validationException;
  }

}
