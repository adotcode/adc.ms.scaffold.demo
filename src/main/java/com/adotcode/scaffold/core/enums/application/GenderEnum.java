package com.adotcode.scaffold.core.enums.application;

import lombok.Getter;

/**
 * 性别
 *
 * @author risfeng
 * @date 2019/08/25
 */
@Getter
public enum GenderEnum {
  /**
   * 未知
   */
  UNKNOWN(0, "application.enum.application.gender.unknown"),
  /**
   * 男
   */
  Male(1, "application.enum.application.gender.male"),
  /**
   * 女
   */
  FEMALE(2, "application.enum.application.gender.female"),
  /**
   * 女变男
   */
  FEMALE_TO_MALE(5, "application.enum.application.gender.femaleToMale"),
  /**
   * 男变女
   */
  MALE_TO_feMALE(6, "application.enum.application.gender.maleToFemale"),
  /**
   * 未说明的
   */
  UNSPECIFIED(9, "application.enum.application.gender.unspecified");

  /**
   * 值
   */
  private Integer value;
  /**
   * 显示名称
   */
  private String displayName;

  GenderEnum(Integer value, String displayName) {
    this.value = value;
    this.displayName = displayName;
  }
}
