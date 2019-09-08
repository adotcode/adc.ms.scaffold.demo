package com.adotcode.scaffold.service.application.impl;

import com.adotcode.scaffold.service.application.I18nService;
import com.adotcode.scaffold.core.enums.i18n.LanguageEnum;
import com.adotcode.scaffold.core.util.i18n.I18nMessageUtils;
import com.adotcode.scaffold.facade.model.output.application.KeyValueOutput;
import com.adotcode.scaffold.facade.model.output.application.LanguageMessageSourceOutput;
import com.adotcode.scaffold.facade.model.output.application.LanguageOutput;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

/**
 * I18n服务接口实现
 *
 * @author risfeng
 * @date 2019/08/07
 */
@Service
public class I18nServiceImpl implements I18nService {

  private final ResourceBundleMessageSource messageSource;

  public I18nServiceImpl(
      ResourceBundleMessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * 获取语言列表
   *
   * @return List<LanguageOutput>
   */
  @Override
  public List<LanguageOutput> getLanguages() {
    return Stream.of(LanguageEnum.values())
        .map(languageEnum -> {
          return new LanguageOutput(
              languageEnum.getLanguage(),
              I18nMessageUtils.translate(languageEnum.getDisplayName()),
              languageEnum.getLang(),
              languageEnum.getCountry(),
              languageEnum.currentLocale());
        })
        .collect(Collectors.toList());
  }

  /**
   * 获取语言资源列表
   *
   * @return LanguageMessageSourceOutput
   */
  @Override
  public LanguageMessageSourceOutput getLocaleMessageResources() {
    Set<String> basenameSet = messageSource.getBasenameSet();
    Locale locale = LocaleContextHolder.getLocale();
    Iterator baseNameIterator = basenameSet.iterator();
    List<KeyValueOutput> keyValueRespons = new LinkedList<>();
    while (baseNameIterator.hasNext()) {
      String baseName = baseNameIterator.next().toString();
      ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);
      Enumeration<String> resourceBundleKeys = resourceBundle.getKeys();
      while (resourceBundleKeys.hasMoreElements()) {
        String key = resourceBundleKeys.nextElement();
        String value = resourceBundle.getString(key);
        keyValueRespons.add(KeyValueOutput.builder().key(key).value(value).build());
      }
    }
    return LanguageMessageSourceOutput.builder()
        .locale(locale.toString())
        .messageSources(keyValueRespons)
        .build();
  }
}
