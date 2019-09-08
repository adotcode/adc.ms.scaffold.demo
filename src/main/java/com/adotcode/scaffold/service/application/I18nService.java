package com.adotcode.scaffold.service.application;

import com.adotcode.scaffold.facade.model.output.application.LanguageMessageSourceOutput;
import com.adotcode.scaffold.facade.model.output.application.LanguageOutput;
import java.util.List;

/**
 * I18n服务接口
 *
 * @author risfeng
 * @date 2019/08/07
 */
public interface I18nService {

  /**
   * 获取语言列表
   *
   * @return List<LanguageOutput>
   */
  List<LanguageOutput> getLanguages();

  /**
   * 获取语言资源列表
   *
   * @return LanguageMessageSourceOutput
   */
  LanguageMessageSourceOutput getLocaleMessageResources();
}
