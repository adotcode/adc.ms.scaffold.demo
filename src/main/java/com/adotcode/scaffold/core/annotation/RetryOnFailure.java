package com.adotcode.scaffold.core.annotation;

import com.adotcode.scaffold.core.exception.application.OptimisticLockException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 失败后重试注解
 *
 * @author risfeng
 * @date 2019/08/19
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RetryOnFailure {

  /**
   * 重试次数，默认：3次
   *
   * @return {@code int}
   */
  int retries() default 3;

  /**
   * 遇到什么异常触发重试，默认：{@code OptimisticLockException}
   *
   * @return Class<? extends Throwable>[]
   */
  Class<? extends Throwable>[] retryFor() default {OptimisticLockException.class};

}
