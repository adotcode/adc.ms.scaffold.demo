package com.adotcode.scaffold.core.filter;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 日志mdc过滤器
 *
 * @author risfeng
 * @date 2019/09/01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogFilter extends OncePerRequestFilter {

  private final String responseHeader;
  private final String requestHeader;
  private final String mdcTrackKey;

  @Override
  protected void doFilterInternal(
      @NonNull final HttpServletRequest request,
      @NonNull final HttpServletResponse response,
      @NonNull final FilterChain chain) throws IOException, ServletException {
    try {
      final String trackId;
      if (!StringUtils.isEmpty(requestHeader) &&
          !StringUtils.isEmpty(request.getHeader(requestHeader))) {
        trackId = request.getHeader(requestHeader);
      } else {
        trackId = UUID.randomUUID().toString().replace("-", "");
      }
      MDC.put(mdcTrackKey, trackId);
      if (!StringUtils.isEmpty(responseHeader)) {
        response.addHeader(responseHeader, trackId);
      }
      chain.doFilter(request, response);
    } finally {
      MDC.remove(mdcTrackKey);
    }
  }
}
