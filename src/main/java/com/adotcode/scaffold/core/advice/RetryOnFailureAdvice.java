package com.adotcode.scaffold.core.advice;

import com.adotcode.scaffold.core.annotation.RetryOnFailure;
import com.adotcode.scaffold.core.enums.result.ResultCodeEnum;
import com.adotcode.scaffold.core.exception.application.GenericException;
import com.adotcode.scaffold.core.util.i18n.I18nMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 失败重试注解实现
 *
 * @author risfeng
 * @date 2019/08/19
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class RetryOnFailureAdvice implements Ordered {

  /**
   * 失败后重试注解实现
   *
   * @param proceedingJoinPoint 切入点
   * @param retryOnFailure 重试注解
   * @return Object 处理结果
   * @throws Throwable 异常
   */
  @Around("@annotation(retryOnFailure)")
  @Transactional(rollbackFor = Exception.class)
  public Object proceed(ProceedingJoinPoint proceedingJoinPoint, RetryOnFailure retryOnFailure)
      throws Throwable {
    int tempCount = 0;
    do {
      tempCount++;
      try {
        //再次执行业务代码
        return proceedingJoinPoint.proceed();
      } catch (Exception ex) {
        //当前异常包含在重试异常中才执行
        if (ArrayUtils.contains(retryOnFailure.retryFor(), ex.getClass())) {
          if (tempCount > retryOnFailure.retries()) {
            //如果大于 默认的重试机制 次数，我们这回就真正的抛出去了
            log.error(I18nMessageUtils.translate(ResultCodeEnum.ERROR_RETRY_FAILED.reasonPhrase()),
                ex);
            throw new GenericException(ResultCodeEnum.ERROR_RETRY_FAILED);
          } else {
            //如果 没达到最大的重试次数，将再次执行
            log.info("Retrying the [{}] time...", tempCount);
          }
        }
      }
    } while (tempCount <= retryOnFailure.retries());
    return null;
  }


  /**
   * Get the order value of this object.
   * <p>Higher values are interpreted as lower priority. As a consequence,
   * the object with the lowest value has the highest priority (somewhat analogous to Servlet {@code
   * load-on-startup} values).
   * <p>Same order values will result in arbitrary sort positions for the
   * affected objects.
   *
   * @return the order value
   * @see #HIGHEST_PRECEDENCE
   * @see #LOWEST_PRECEDENCE
   */
  @Override
  public int getOrder() {
    return 1;
  }
}
