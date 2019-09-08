package com.adotcode.scaffold.core.exception.application;

import com.adotcode.scaffold.core.enums.result.ResultCodeEnum;

/**
 * 禁止访问异常处理
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-22
 */
public class ForbiddenException extends BaseException {

  private static final long serialVersionUID = -7472953075828927558L;

  /**
   * default message:无权访问
   */
  public ForbiddenException() {
    super(ResultCodeEnum.FORBIDDEN);
  }

  /**
   * custom message
   */
  public ForbiddenException(String message) {
    super(ResultCodeEnum.FORBIDDEN, message);
  }
}
