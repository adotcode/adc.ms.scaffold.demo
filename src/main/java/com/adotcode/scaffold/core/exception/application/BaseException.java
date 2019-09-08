package com.adotcode.scaffold.core.exception.application;

import com.adotcode.scaffold.core.enums.result.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础异常抽象类(未检查异常,代码简洁，一些流行的Java框架（Spring或Hibernate）甚至其他语言根本不使用已检查的异常)
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseException extends RuntimeException {

  private static final long serialVersionUID = 9053394735482735363L;

  /**
   * exception code
   */
  private String code;

  /**
   * exception message
   */
  private String message;

  /**
   * time stamp millis(js中long型会造成精度损失)
   */
  private final String timestamp = String.valueOf(System.currentTimeMillis());

  /**
   * @param code {@code ResultCodeEnum} ResultCodeEnum
   */
  BaseException(ResultCodeEnum code) {
    super(code.reasonPhrase());
    this.code = code.value();
    this.message = code.reasonPhrase();
  }

  /**
   * custom message
   *
   * @param code exception code
   * @param message custom message
   */
  BaseException(ResultCodeEnum code, String message) {
    super(message);
    this.code = code.value();
    this.message = message;
  }

  /**
   * @param code exception code
   * @param e exception
   */
  BaseException(ResultCodeEnum code, Exception e) {
    super(e);
    this.code = code.value();
    this.message = code.reasonPhrase();
  }
}
