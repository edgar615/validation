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
@Constraint(validatedBy = DateConstraintValidator.class)
public @interface DateValidator {

  /**
   * 必须的属性 显示 校验信息 利用 {} 获取 属性值，参考了官方的message编写方式
   *
   * @see org.hibernate.validator 静态资源包里面 message 编写方式
   */
  String message() default "日期格式不匹配{format}";

  /**
   * 必须的属性 用于分组校验
   */
  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * 非必须
   */
  String format() default "yyyy-MM-dd";

}
