package com.adotcode.scaffold.core.annotation;

import com.adotcode.scaffold.core.database.DataSourceTypeEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切换数据源注解
 *
 * @author risfeng
 * @date 2019/08/11
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwitchDataSource {

  /**
   * 数据源类型，默认：{@code DataSourceTypeEnum.MASTER}
   *
   * @return {@code DataSourceTypeEnum}
   */
  DataSourceTypeEnum value() default DataSourceTypeEnum.MASTER;
}
