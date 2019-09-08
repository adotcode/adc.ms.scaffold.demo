package com.adotcode.scaffold.core.enums.application;

import lombok.Getter;

/**
 * 标识：是/否、启用/禁用等枚举
 *
 * @author risfeng
 * @date 2019/08/25
 */
@Getter
public enum YesNoEnum {

  /**
   * 否/禁用
   */
  NO(0, "application.enum.application.yesOrNo.no"),

  /**
   * 是/启用
   */
  YES(1, "application.enum.application.yesOrNo.yes");

  /**
   * 枚举值
   */
  private Integer value;

  /**
   * 显示名称
   */
  private String displayName;

  YesNoEnum(Integer value, String displayName) {
    this.value = value;
    this.displayName = displayName;
  }
}
