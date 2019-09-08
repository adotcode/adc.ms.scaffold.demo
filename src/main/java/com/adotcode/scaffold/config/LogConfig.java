package com.adotcode.scaffold.config;

import com.adotcode.scaffold.core.filter.LogFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志配置
 *
 * @author risfeng
 * @date 2019/08/31
 */
@Configuration
public class LogConfig {

  /**
   * 响应日志跟踪Id头名称
   */
  @Value("${logging.config.filter.response-header:X-Track-Id}")
  private String responseTrackHeader;

  /**
   * 上游请求 日志跟踪Id头名称
   */
  @Value("${logging.config.filter.request-header:X-Track-Id}")
  private String requestTrackHeader;

  /**
   * mdc中跟踪唯一键名（key）
   */
  @Value("${logging.config.filter.mdc-track-key:track-id}")
  private String mdcTrackKey;

  @Bean
  public FilterRegistrationBean mdcLogFilterRegistrationBean() {
    final FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
    final LogFilter logFilter = new LogFilter(responseTrackHeader, requestTrackHeader, mdcTrackKey);
    registrationBean.setFilter(logFilter);
    registrationBean.setOrder(2);
    return registrationBean;
  }
}
