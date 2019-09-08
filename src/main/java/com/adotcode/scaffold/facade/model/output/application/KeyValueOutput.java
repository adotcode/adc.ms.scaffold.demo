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
 * 键值对输出
 *
 * @author risfeng
 * @date 2019/08/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "键值对输出模型", description = "键值对输出模型,适用于key-value形式的输出.")
public class KeyValueOutput implements Serializable {

  private static final long serialVersionUID = -5863330394462055403L;

  /**
   * 键
   */
  @ApiModelProperty(value = "键（Key）", dataType = "Object", required = true)
  private Object key;

  /**
   * 值
   */
  @ApiModelProperty(value = "值（value）", dataType = "Object")
  private Object value;

  /**
   * 是否选中
   */
  @ApiModelProperty(value = "是否选中，一般实用于选择场景", dataType = "boolean")
  private boolean selected;

  /**
   * json 序列化
   */
  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }
}
