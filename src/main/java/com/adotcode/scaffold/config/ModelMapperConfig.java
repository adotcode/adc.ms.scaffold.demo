package com.adotcode.scaffold.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 实体转换配置
 *
 * @author risfeng
 * @date 2019/08/08
 */
@Configuration
public class ModelMapperConfig {

  @Bean
  @Scope("singleton")
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
    // 类型映射代码
    sourceToDestination(modelMapper);
    //验证映射
    modelMapper.validate();
    return modelMapper;
  }

  /**
   * 声明 Source 类转 Destination 类的 Mapper
   */
  private void sourceToDestination(ModelMapper modelMapper) {
    // demo
//    modelMapper.createTypeMap(s.class, d.class)
//        .addMapping(s::getSonId, d::setDsonId)
//        .addMapping(s::getSonName, d::setSonName)
//        .addMappings(mapper -> mapper.skip(d::setExcessParam));
  }
}
