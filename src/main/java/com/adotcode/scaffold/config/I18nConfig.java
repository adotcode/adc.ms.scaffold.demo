package com.adotcode.scaffold.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * I18n配置
 *
 * @author risfeng
 * @date 2019/08/04
 */
@Configuration
@Slf4j
public class I18nConfig implements WebMvcConfigurer {

  /**
   * 指定的国际化文件目录, 默认是i18n文件夹
   */
  @Value("${spring.messages.base-folder:i18n}")
  private String baseFolder;

  /**
   * 配置cookie语言解析器
   *
   * @return LocaleResolver
   */
  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver resolver = new CookieLocaleResolver();
    // cookie有效时长，单位秒
    resolver.setCookieMaxAge(36000);
    //设置存储的Cookie的name为Language
    resolver.setCookieName("Language");
    //默认语言
    resolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
    return resolver;
  }

  /**
   * 配置一个拦截器，拦截国际化语言的变化
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("l");
    registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/**");
  }

  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    if (!StringUtils.isEmpty(baseFolder)) {
      try {
        String[] baseNames = getAllBaseNames(baseFolder);
        messageSource.setBasenames(baseNames);
        messageSource.setDefaultEncoding("UTF-8");
        //使用消息key作为默认值
        messageSource.setUseCodeAsDefaultMessage(true);
      } catch (IOException e) {
        log.error("i18n set base names error.", e);
      }
    }
    return messageSource;
  }

  /**
   * 获取文件夹下所有的国际化文件名
   *
   * @param folderName 文件名
   */
  private String[] getAllBaseNames(String folderName) throws IOException {
    Resource resource = new ClassPathResource(folderName);
    File file = resource.getFile();
    List<String> baseNames = new ArrayList<>();
    if (file.exists() && file.isDirectory()) {
      this.getAllFile(baseNames, file, "");
    } else {
      log.error("i18n base file path not exist or not directory.");
    }
    return baseNames.toArray(new String[0]);
  }

  /**
   * 遍历所有文件
   */
  private void getAllFile(List<String> baseNames, File folder, String path) {
    if (folder.isDirectory()) {
      File[] files = folder.listFiles();
      if (files == null) {
        return;
      }
      for (File file : files) {
        this.getAllFile(baseNames, file, path + folder.getName() + File.separator);
      }
    } else {
      String i18Name = getI18FileName(path + folder.getName());
      if (!baseNames.contains(i18Name)) {
        baseNames.add(i18Name);
      }
    }
  }

  /**
   * 把普通文件名转换成国际化文件名
   *
   * @return 文件名
   */
  private String getI18FileName(String filename) {
    filename = filename.replace(".properties", "");
    int index = filename.indexOf("_");
    if (index > -1) {
      filename = filename.substring(0, index);
    }
    return filename;
  }
}
