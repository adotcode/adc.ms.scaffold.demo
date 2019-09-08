package com.adotcode.scaffold.core.advice;

import com.adotcode.scaffold.core.annotation.SwitchDataSource;
import com.adotcode.scaffold.core.database.DataSourceContextHolder;
import com.adotcode.scaffold.core.util.i18n.I18nMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 执行注解实现数据源动态切换
 *
 * @author risfeng
 * @date 2019/08/11
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class DynamicDataSourceAdvice implements Ordered {

  /**
   * 切换数据源注解实现
   *
   * @param proceedingJoinPoint 切入点
   * @param switchDataSource 注解
   * @return Object 处理结果
   * @throws Throwable 异常
   */
  @Around("@annotation(switchDataSource)")
  public Object proceed(ProceedingJoinPoint proceedingJoinPoint, SwitchDataSource switchDataSource)
      throws Throwable {
    try {
      log.info(I18nMessageUtils
          .translate("application.database.datasource.switch.success", switchDataSource.value()));
      DataSourceContextHolder.setDataSource(switchDataSource.value());
      // 让这个方法执行完
      return proceedingJoinPoint.proceed();
    } catch (Exception e) {
      log.error(I18nMessageUtils.translate("application.database.datasource.switch.error"), e);
      throw e;
    } finally {
      DataSourceContextHolder.clear();
      log.info(I18nMessageUtils
          .translate("application.database.datasource.clear.success", switchDataSource.value()));
    }
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
