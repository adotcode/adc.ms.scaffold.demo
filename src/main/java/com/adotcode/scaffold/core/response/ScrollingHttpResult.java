package com.adotcode.scaffold.core.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 滚动分页加载结果返回包装器
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-15
 */
@NoArgsConstructor
public class ScrollingHttpResult<T> extends HttpResult<ScrollingHttpResult.ScrollingResult<T>> {

  /**
   * 默认返回量
   */
  private static final long DEFAULT_TAKE = 10;

  /**
   * 滚动分页返回实体
   *
   * @param <T> 返回类型
   */
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  static class ScrollingResult<T> {

    /**
     * 分页数据集
     */
    private List<T> list;
    /**
     * 下一次开始位置
     */
    private String nextToken;

    /**
     * 每次取量
     */
    private long take;

  }

  /**
   * ok result
   *
   * @param data list data
   * @param nextToken next start position
   */
  private ScrollingHttpResult(List<T> data, String nextToken) {
    super(new ScrollingHttpResult.ScrollingResult<>(data, nextToken, DEFAULT_TAKE));
  }

  /**
   * ok result
   *
   * @param data list data
   * @param nextToken next start position
   * @param take per get data count
   */
  private ScrollingHttpResult(List<T> data, String nextToken, long take) {
    super(new ScrollingHttpResult.ScrollingResult<>(data, nextToken, take));
  }

  /**
   * ok result
   *
   * @param data list data
   * @param nextToken next start position
   */
  public static <T> ScrollingHttpResult<T> ok(List<T> data, String nextToken) {
    return new ScrollingHttpResult<>(data, nextToken);
  }

  /**
   * ok result
   *
   * @param data list data
   * @param nextToken next start position
   * @param take per get data count
   */
  public static <T> ScrollingHttpResult<T> ok(List<T> data, String nextToken, long take) {
    return new ScrollingHttpResult<>(data, nextToken, take);
  }
}

