package com.adotcode.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * main
 * <p>
 * exclude = {DataSourceAutoConfiguration.class}
 * <p>
 * 禁用springboot默认加载的application.yml单数据源配置
 * <p>@MapperScan({"com.adotcode.scaffold.domain.mapper"})
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-13
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ScaffoldApplication {

  public static void main(String[] args) {
    SpringApplication.run(ScaffoldApplication.class, args);
  }

}
