package com.adotcode.scaffold.facade.model.output.application;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 本地化语言信息 key-value
 *
 * @author risfeng
 * @date 2019/08/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "语言资源信息输出模型", description = "语言资源信息键值对列表输出")
public class LanguageMessageSourceOutput implements Serializable {

  private static final long serialVersionUID = -4559951472817760421L;

  /**
   * 当前语言编码
   */
  @ApiModelProperty(value = "当前语言编码，eg：zh_CN", dataType = "String", example = "zh_CN")
  private String locale;

  /**
   * 语言信息列表
   */
  @ApiModelProperty(value = "语言资源信息列表", dataType = "List<KeyValueOutput>")
  private List<KeyValueOutput> messageSources;

  /**
   * json 序列化
   */
  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }
}
