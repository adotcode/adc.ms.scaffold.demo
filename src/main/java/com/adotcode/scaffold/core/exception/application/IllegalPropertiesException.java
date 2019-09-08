package com.adotcode.scaffold.core.exception.application;

import com.adotcode.scaffold.core.enums.result.ResultCodeEnum;

/**
 * 非法属性异常
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-21
 */
public class IllegalPropertiesException extends BaseException {

  private static final long serialVersionUID = 6467571653798792346L;

  /**
   * default message:非法的属性
   */
  public IllegalPropertiesException() {
    super(ResultCodeEnum.ILLEGAL_PROPERTIES);
  }

  /**
   * custom propertiesName
   *
   * @param propertiesName 属性名
   */
  public IllegalPropertiesException(String propertiesName) {
    super(ResultCodeEnum.ILLEGAL_PROPERTIES, String.format("属性[%s]非法.", propertiesName));
  }
}
