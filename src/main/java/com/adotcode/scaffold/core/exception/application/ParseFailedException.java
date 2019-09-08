package com.adotcode.scaffold.core.exception.application;

import com.adotcode.scaffold.core.enums.result.ResultCodeEnum;
import java.text.ParseException;

/**
 * 转换失败异常
 *
 * @author risfeng
 * @date 2019/08/17
 */
public class ParseFailedException extends BaseException {

  private static final long serialVersionUID = -6599940145228665597L;

  /**
   * 转换失败异常处理 {@code ResultCodeEnum.PARSE_FAILED}
   */
  public ParseFailedException(ParseException e) {
    super(ResultCodeEnum.PARSE_FAILED, e);
  }

  /**
   * 转换失败异常处理 {@code ResultCodeEnum.PARSE_FAILED}
   */
  public ParseFailedException() {
    super(ResultCodeEnum.PARSE_FAILED);
  }

  /**
   * 转换失败异常处理
   *
   * @param code {@code ResultCodeEnum}
   */
  public ParseFailedException(ResultCodeEnum code) {
    super(code);
  }

  /**
   * 转换失败异常处理
   *
   * @param message message
   */
  public ParseFailedException(String message) {
    super(ResultCodeEnum.PARSE_FAILED, message);
  }

  /**
   * 转换失败异常处理
   *
   * @param code code
   * @param message message
   */
  public ParseFailedException(ResultCodeEnum code, String message) {
    super(code, message);
  }
}
