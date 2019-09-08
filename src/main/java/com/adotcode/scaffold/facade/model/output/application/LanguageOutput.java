package com.adotcode.scaffold.facade.model.output.application;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 本地化语言
 *
 * @author risfeng
 * @date 2019/08/07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "本地化语言编码输出模型", description = "本地化语言编码列表信息输出模型")
public class LanguageOutput implements Serializable {

  private static final long serialVersionUID = -1109056834909541664L;

  /**
   * 本地化编码
   */
  @ApiModelProperty(value = "本地化编码，eg：zh_CN", dataType = "String", example = "zh_CN")
  private String locale;

  /**
   * 显示名称
   */
  @ApiModelProperty(value = "显示名称", dataType = "String", example = "简体中文")
  private String displayName;

  /**
   * 语言编码
   */
  @ApiModelProperty(value = "语言编码，eg：zh", dataType = "String", example = "zh")
  private String language;
  /**
   * 国家码
   */
  @ApiModelProperty(value = "国家码，eg：CN", dataType = "String", example = "CN")
  private String country;

  /**
   * 是否当前使用语言
   */
  @ApiModelProperty(value = "是否当前使用语言", dataType = "boolean", example = "true")
  private boolean currentLocale;

  /**
   * json 序列化
   */
  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }
}
