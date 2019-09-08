package com.adotcode.scaffold.core.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 列表结果返回包装器
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-14
 */
@NoArgsConstructor
public class ListHttpResult<T> extends HttpResult<ListHttpResult.ListResult<T>> {

  /**
   * 列表返回
   *
   * @param <T> entity type
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static class ListResult<T> {

    /**
     * 列表数据集
     */
    private List<T> list;
  }

  /**
   * 成功返回
   *
   * @param data 列表数据
   */
  private ListHttpResult(List<T> data) {
    super(new ListHttpResult.ListResult<>(data));
  }

  /**
   * ok result
   *
   * @param data data
   * @param <T> object T
   * @return HttpResult<T>
   */
  public static <T> ListHttpResult<T> ok(List<T> data) {
    return new ListHttpResult<>(data);
  }
}
