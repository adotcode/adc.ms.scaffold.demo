package com.adotcode.scaffold.core.util.i18n;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * 多语言国际化消息工具类
 *
 * @author risfeng
 * @date 2019/08/04
 */
@Component
public class I18nMessageUtils {

  private static ResourceBundleMessageSource messageSource;

  @Autowired
  I18nMessageUtils(ResourceBundleMessageSource messageSource) {
    I18nMessageUtils.messageSource = messageSource;
  }

  /**
   * 翻译
   *
   * @param msgCode locale key
   * @return locale message
   */
  public static String translate(String msgCode) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(msgCode, null, locale);
  }

  /**
   * 翻译
   *
   * @param msgCode locale key eg:good.morning.name={0} Good morning {1}
   * @param args string format 参数 eg:String[] params= new String[]{“tom”, “today”}
   * @return locale message eg:tom Good morning today
   */
  public static String translate(String msgCode, Object... args) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(msgCode, args, locale);
  }

}
