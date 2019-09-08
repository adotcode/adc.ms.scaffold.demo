package com.adotcode.scaffold.core.exception.application;

import com.adotcode.scaffold.core.enums.result.ResultCodeEnum;

/**
 * 通用异常
 *
 * @author risfeng
 * @date 2019/07/25
 */
public class GenericException extends BaseException {

  private static final long serialVersionUID = -3955289008909707402L;

  /**
   * 通用异常处理 {@code ResultCodeEnum.ERROR}
   */
  public GenericException() {
    super(ResultCodeEnum.ERROR);
  }

  /**
   * 通用异常处理
   *
   * @param code {@code ResultCodeEnum}
   */
  public GenericException(ResultCodeEnum code) {
    super(code);
  }

  /**
   * 通用异常处理
   *
   * @param message message
   */
  public GenericException(String message) {
    super(ResultCodeEnum.ERROR, message);
  }

  /**
   * 通用异常处理
   *
   * @param code code
   * @param message message
   */
  public GenericException(ResultCodeEnum code, String message) {
    super(code, message);
  }
}
