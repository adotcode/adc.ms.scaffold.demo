package com.adotcode.scaffold.config;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

/**
 * Json配置
 *
 * @author risfeng
 * @date 2019/07/28
 */
@Configuration
public class JsonConfig {

  /**
   * Json时间格式化格式
   */
  @Value("${json.date-format}")
  private String dateFormat;

  /**
   * 配置全局使用FastJson
   *
   * @return HttpMessageConverters
   */
  @Bean
  public HttpMessageConverters fastJsonHttpMessageConverters() {
    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    //设置格式化日期
    fastJsonConfig.setDateFormat(dateFormat);
    //特性设置
    fastJsonConfig.setFeatures(
        Feature.OrderedField,
        Feature.IgnoreNotMatch
    );
    //设置序列化时特性
    fastJsonConfig.setSerializerFeatures(
        SerializerFeature.PrettyFormat,
        SerializerFeature.WriteBigDecimalAsPlain,
        SerializerFeature.WriteDateUseDateFormat,
        SerializerFeature.WriteEnumUsingToString
    );
    //序列化设置
    SerializeConfig serializeConfig = SerializeConfig.globalInstance;
    serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.CamelCase;
    fastJsonConfig.setSerializeConfig(serializeConfig);

    //反序列化设置
    ParserConfig parserConfig = ParserConfig.getGlobalInstance();
    parserConfig.propertyNamingStrategy = PropertyNamingStrategy.CamelCase;
    fastJsonConfig.setParserConfig(parserConfig);
    //"autoType is not support"问题,使用setAutoTypeSupport=true的全局设置
    parserConfig.setAutoTypeSupport(true);

    //中文乱码处理
    ArrayList<MediaType> fastMediaTypes = new ArrayList<>();
    fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
    fastConverter.setSupportedMediaTypes(fastMediaTypes);
    fastConverter.setFastJsonConfig(fastJsonConfig);
    return new HttpMessageConverters(fastConverter);
  }
}
