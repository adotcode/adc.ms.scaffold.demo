package com.adotcode.scaffold.core.exception.application;

import com.adotcode.scaffold.core.enums.result.ResultCodeEnum;

/**
 * 乐观锁更新异常
 *
 * @author risfeng
 * @date 2019/08/19
 */
public class OptimisticLockException extends BaseException {

  private static final long serialVersionUID = -8698351229615133053L;

  /**
   * 数据更新失败，请重试。
   */
  public OptimisticLockException() {
    super(ResultCodeEnum.DATA_UPDATE_FAILED);
  }

  /**
   * custom message
   */
  public OptimisticLockException(String message) {
    super(ResultCodeEnum.DATA_UPDATE_FAILED, message);
  }
}
