package com.adotcode.scaffold.core.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 *
 * @author risfeng
 * @date 2019/08/11
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
    return DataSourceContextHolder.getDataSource();
  }
}
