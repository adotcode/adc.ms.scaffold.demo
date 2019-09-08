package com.adotcode.scaffold.core.cache;

import com.adotcode.scaffold.core.constant.ApplicationConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * FastJson2JsonRedisSerializer
 * <p>
 * Redis使用FastJson序列化
 * </p>
 *
 * @author risfeng
 * @date 2019/09/08
 */
public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {

  private Class<T> clazz;

  static {
    //方案1："autoType is not support"问题,使用setAutoTypeSupport=true的全局设置
    ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    //方案2：如果遇到反序列化autoType is not support错误，请添加并修改一下包名到bean文件路径
    //ParserConfig.getGlobalInstance().addAccept("com.adotcode.scaffold.");
  }

  public FastJson2JsonRedisSerializer(Class<T> clazz) {
    super();
    this.clazz = clazz;
  }

  @Override
  public byte[] serialize(T t) throws SerializationException {
    if (t == null) {
      return new byte[0];
    }
    return JSON.toJSONString(t).getBytes(ApplicationConstants.CHARSET);
  }

  @Override
  public T deserialize(byte[] bytes) throws SerializationException {
    if (bytes == null || bytes.length <= 0) {
      return null;
    }
    String str = new String(bytes, ApplicationConstants.CHARSET);
    return JSON.parseObject(str, clazz);
  }
}
