package com.adotcode.scaffold.core.util.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 当前请求工具类
 *
 * @author risfeng
 * @date 2019/08/09
 */
public class RequestUtils {

  /**
   * 获取当前请求
   *
   * @return HttpServletRequest
   */
  public static HttpServletRequest getCurrentRequest() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    Assert.state(requestAttributes != null,
        "Could not find current request via RequestContextHolder");
    Assert.isInstanceOf(ServletRequestAttributes.class, requestAttributes);
    return ((ServletRequestAttributes) requestAttributes).getRequest();
  }


  /**
   * 获取请求Uri
   *
   * @return RequestURI
   */
  public static String getRequestUri() {
    return getCurrentRequest().getRequestURI();
  }

  /**
   * 获取请求Url
   *
   * @return RequestURL
   */
  public static String getRequestUrl() {
    return getCurrentRequest().getRequestURL().toString();
  }

  /**
   * 获取请求完整Url（包括参数）
   *
   * @return RequestURL
   */
  public static String getRequestAllUrl() {
    String queryString = getCurrentRequest().getQueryString();
    return String.format("%s%s",
        getRequestUrl(),
        StringUtils.isEmpty(queryString) ? "" : ("?" + queryString)
    );
  }

  /**
   * 获取用户Ip地址
   *
   * @return IP Address
   */
  public static String getIpAddress() {
    return getCurrentRequest().getRemoteAddr();
  }

  /**
   * 获取用户浏览器
   *
   * @return UserAgent
   */
  public static String getUserAgent() {
    return getCurrentRequest().getHeader("user-agent");
  }

  /**
   * 获取Session
   *
   * @return HttpSession
   */
  public static HttpSession getSession() {
    return getCurrentRequest().getSession();
  }
}
