package com.adotcode.scaffold.core.database;

import com.adotcode.scaffold.core.util.i18n.I18nMessageUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据源处理器
 *
 * @author risfeng
 * @date 2019/08/10
 */
@Slf4j
public class DataSourceContextHolder {

  /**
   * 默认数据源
   */
  public static final DataSourceTypeEnum DEFAULT_DATA_SOURCE = DataSourceTypeEnum.MASTER;

  /**
   * 线程局部变量
   */
  private static final ThreadLocal<DataSourceTypeEnum> CONTEXT_HOLDER = new ThreadLocal<>();

  /**
   * 往线程里边set数据源类型
   *
   * @param dataSource 数据源
   */
  public static void setDataSource(DataSourceTypeEnum dataSource) {
    if (dataSource == null) {
      throw new NullPointerException();
    }
    String logInfo = I18nMessageUtils
        .translate("application.database.datasource.change", dataSource.name());
    log.info(logInfo);
    CONTEXT_HOLDER.set(dataSource);
  }

  /**
   * 从容器中获取数据类型
   *
   * @return DataSourceTypeEnum
   */
  public static DataSourceTypeEnum getDataSource() {
    return CONTEXT_HOLDER.get() == null ? DEFAULT_DATA_SOURCE : CONTEXT_HOLDER.get();
  }

  /**
   * 清空容器中的数据类型
   */
  public static void clear() {
    CONTEXT_HOLDER.remove();
  }
}
