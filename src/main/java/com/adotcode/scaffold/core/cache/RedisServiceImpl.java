package com.adotcode.scaffold.core.cache;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * Redis通用服务接口实现
 *
 * @author risfeng
 * @date 2019/09/08
 */
@Service
public class RedisServiceImpl<THashKey, TValue> implements RedisService<THashKey, TValue> {

  /**
   * 是否使用redis key前缀
   */
  @Value("${spring.cache.redis.use-key-prefix}")
  private Boolean useKeyPrefix;

  /**
   * redis key前缀
   */
  @Value("${spring.cache.redis.key-prefix}")
  private String keyPrefix;

  /**
   * 在构造器中获取redisTemplate实例, key(not hashKey) 默认使用String类型
   */
  private RedisTemplate<String, TValue> redisTemplate;
  /**
   * 在构造器中通过redisTemplate的工厂方法实例化操作对象
   * <p>
   * 可自己扩展
   * </p>
   */
  private HashOperations<String, THashKey, TValue> hashOperations;
  private ListOperations<String, TValue> listOperations;
  private ValueOperations<String, TValue> valueOperations;
  private ValueOperations<String, String> valueStingOperations;

  @Override
  public void setValueSerializerType(Class<TValue> valueClass) {
    FastJson2JsonRedisSerializer valueSerializer = new FastJson2JsonRedisSerializer<>(valueClass);
    redisTemplate.setValueSerializer(valueSerializer);
    redisTemplate.setHashValueSerializer(valueSerializer);
  }

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public RedisServiceImpl(
      StringRedisTemplate stringRedisTemplate,
      RedisTemplate<String, TValue> redisTemplate) {
    this.redisTemplate = redisTemplate;
    this.hashOperations = redisTemplate.opsForHash();
    this.listOperations = redisTemplate.opsForList();
    this.valueOperations = redisTemplate.opsForValue();
    this.valueStingOperations = stringRedisTemplate.opsForValue();
  }

  /**
   * 设置哈希列表
   *
   * @param key redis Key 不可为空
   * @param hashKey 哈希Key 不可为空
   * @param value value
   */
  @Override
  public void hashPut(
      @NotNull(message = "key not null") String key,
      THashKey hashKey,
      TValue value) {
    key = getFullKey(key);
    hashOperations.put(key, hashKey, value);
  }

  /**
   * 通过key查找哈希列表
   *
   * @param key redis key
   * @return {@code Map<THashKey, TValue>} 列表
   */
  @Override
  public Map<THashKey, TValue> hashFindAll(@NotNull(message = "key not null") String key) {
    key = getFullKey(key);
    return hashOperations.entries(key);
  }

  /**
   * 通过 key 和 哈希key 获取值
   *
   * @param key redis key
   * @param hashKey 哈希key
   * @return {@code TValue} 值
   */
  @Override
  public TValue hashGet(@NotNull(message = "key not null") String key, THashKey hashKey) {
    key = getFullKey(key);
    return hashOperations.get(key, hashKey);
  }

  /**
   * 删除哈希列表中某一个哈希key
   *
   * @param key redis key
   * @param hashKey 哈希key
   */
  @Override
  public void hashDelete(@NotNull(message = "key not null") String key, THashKey hashKey) {
    key = getFullKey(key);
    hashOperations.delete(key, hashKey);
  }

  /**
   * 将所有指定的值插入存储在的列表的尾部key。
   * <p>
   * 如果key不存在，则在执行推送操作之前将其创建为空列表。如果key保存的值不是列表，则返回错误。
   * </p>
   *
   * @param key redis key
   * @param value 待插入的值
   * @return 操作后列表的长度
   */
  @Override
  public Long listPush(@NotNull(message = "key not null") String key, TValue value) {
    key = getFullKey(key);
    return listOperations.rightPush(key, value);
  }

  /**
   * 将所有指定的值插入存储在的列表的头部key。
   * <p>
   * 如果key不存在，则在执行推送操作之前将其创建为空列表。如果key保存的值不是列表，则返回错误。
   * </p>
   *
   * @param key redis key
   * @param value 待插入的值
   * @return 操作后列表的长度
   */
  @Override
  public Long listUnshift(@NotNull(message = "key not null") String key, TValue value) {
    key = getFullKey(key);
    return listOperations.leftPush(key, value);
  }

  /**
   * 返回key的所有元素列表
   *
   * @param key redis key
   * @return {@code List<TValue>} 所有元素列表
   */
  @Override
  public List<TValue> listFindAll(@NotNull(message = "key not null") String key) {
    key = getFullKey(key);
    Boolean hasKey = redisTemplate.hasKey(key);
    if (hasKey == null || !hasKey) {
      return null;
    }
    Long size = listOperations.size(key);
    return listOperations.range(key, 0, size == null ? 0 : size);
  }

  /**
   * 删除并返回存储在列表中的第一个元素key。
   *
   * @param key redis key
   * @return {@code TValue} 第一个元素的值，当key不存在时返回null
   */
  @Override
  public TValue listLeftPop(@NotNull(message = "key not null") String key) {
    key = getFullKey(key);
    return listOperations.leftPop(key);
  }

  /**
   * 设置 {@link TValue} 类型的值
   *
   * @param key redis key
   * @param value 值
   */
  @Override
  public void setValue(@NotNull(message = "key not null") String key, TValue value) {
    key = getFullKey(key);
    valueOperations.set(key, value);
  }

  /**
   * 设置 {@link TValue} 类型的值
   *
   * @param key redis key
   * @param value 值
   * @param timeout key过期时间,单位：毫秒
   */
  @Override
  public void setValue(@NotNull(message = "key not null") String key, TValue value, long timeout) {
    key = getFullKey(key);
    valueOperations.set(key, value, timeout, TimeUnit.MILLISECONDS);
  }

  /**
   * 设置 {@link TValue} 类型的值
   *
   * @param key redis key
   * @param value 值
   * @param timeout key过期时间
   * @param timeUnit key过期时间单位
   */
  @Override
  public void setValue(@NotNull(message = "key not null") String key, TValue value, long timeout,
      TimeUnit timeUnit) {
    key = getFullKey(key);
    valueOperations.set(key, value, timeout, timeUnit);
  }

  /**
   * 设置 {@link String} 类型的值
   *
   * @param key redis key
   * @param value 值
   */
  @Override
  public void setStringValue(@NotNull(message = "key not null") String key, String value) {
    key = getFullKey(key);
    valueStingOperations.set(key, value);
  }

  /**
   * 设置 {@link String} 类型的值
   *
   * @param key redis key
   * @param value 值
   * @param timeout key过期时间,单位：毫秒
   */
  @Override
  public void setStringValue(@NotNull(message = "key not null") String key, String value,
      long timeout) {
    key = getFullKey(key);
    valueStingOperations.set(key, value, timeout, TimeUnit.MILLISECONDS);
  }

  /**
   * 设置 {@link String} 类型的值
   *
   * @param key redis key
   * @param value 值
   * @param timeout key过期时间
   * @param timeUnit key过期时间单位
   */
  @Override
  public void setStringValue(@NotNull(message = "key not null") String key, String value,
      long timeout, TimeUnit timeUnit) {
    key = getFullKey(key);
    valueStingOperations.set(key, value, timeout, timeUnit);
  }

  /**
   * 获取 {@link TValue} 类型的值
   *
   * @param key redis key
   * @return {@link TValue} 值
   */
  @Override
  public TValue getValue(@NotNull(message = "key not null") String key) {
    key = getFullKey(key);
    return valueOperations.get(key);
  }

  /**
   * 获取 {@link String} 类型的值
   *
   * @param key redis key
   * @return {@link String} 值
   */
  @Override
  public String getStringValue(@NotNull(message = "key not null") String key) {
    key = getFullKey(key);
    return valueStingOperations.get(key);
  }

  /**
   * 删除Key
   *
   * @param key redis key
   * @return {@link boolean} 是否成功
   */
  @Override
  public boolean delete(@NotNull(message = "key not null") String key) {
    key = getFullKey(key);
    Boolean result = redisTemplate.delete(key);
    return result != null && result;
  }

  /**
   * 删除Keys
   *
   * @param keys redis keys
   * @return {@link long} 已删除的键数
   */
  @Override
  public long delete(Collection<String> keys) {
    keys = getFullKey(keys);
    Long result = redisTemplate.delete(keys);
    return result == null ? 0 : result;
  }

  /**
   * 设置Key过期时间
   *
   * @param key redis key
   * @param timeout 过期时间
   * @param timeUnit 过期时间单位
   * @return {@link boolean} 是否成功
   */
  @Override
  public boolean expire(@NotNull(message = "key not null") String key, long timeout,
      TimeUnit timeUnit) {
    key = getFullKey(key);
    Boolean expire = redisTemplate.expire(key, timeout, timeUnit);
    return expire != null && expire;
  }

  /**
   * 设置Key过期时间
   *
   * @param key redis key
   * @param expireAt 具体过期日期
   * @return {@link boolean} 是否成功
   */
  @Override
  public boolean expireAt(@NotNull(message = "key not null") String key, Date expireAt) {
    key = getFullKey(key);
    Boolean expire = redisTemplate.expireAt(key, expireAt);
    return expire != null && expire;
  }

  /**
   * Key是否存在
   *
   * @param key redis key
   * @return {@link boolean} 是否存在
   */
  @Override
  public boolean hasKey(@NotNull(message = "key not null") String key) {
    key = getFullKey(key);
    Boolean hasKey = redisTemplate.hasKey(key);
    return hasKey != null && hasKey;
  }

  /**
   * 设置Key为永不过期
   *
   * @param key redis key
   * @return {@link boolean} 是否成功
   */
  @Override
  public boolean persist(@NotNull(message = "key not null") String key) {
    key = getFullKey(key);
    Boolean persist = redisTemplate.persist(key);
    return persist != null && persist;
  }

  /**
   * 返回Key的剩余生存时间,单位：秒
   *
   * @param key redis key
   * @return {@link boolean} 剩余生存时间，以秒为单位，错误返回为负值
   */
  @Override
  public long getExpire(@NotNull(message = "key not null") String key) {
    key = getFullKey(key);
    Long getExpire = redisTemplate.getExpire(key);
    return getExpire == null ? -1 : getExpire;
  }

  /**
   * 返回Key的剩余生存时间
   *
   * @param key redis key
   * @param timeUnit 时间单位
   * @return {@link boolean} 剩余生存时间，错误返回为负值
   */
  @Override
  public long getExpire(@NotNull(message = "key not null") String key, final TimeUnit timeUnit) {
    key = getFullKey(key);
    Long getExpire = redisTemplate.getExpire(key, timeUnit);
    return getExpire == null ? -1 : getExpire;
  }

  /**
   * 获取统一格式的key
   * <p>
   * 每个字符串之间使用:分隔，在Redis管理器中查看时是以目录层级显示，增强可读性。
   * </p>
   *
   * @param keys key列表
   * @return 格式后key
   */
  @Override
  public String getKey(String... keys) {
    return String.join(":", keys);
  }

  /**
   * 获取完整key，在头上加上key前缀。之间用：分隔
   *
   * @param key key
   * @return 在头部追加keyPrefix的key
   */
  private String getFullKey(@NotNull(message = "key not null") String key) {
    if (!useKeyPrefix) {
      return key;
    }
    return String.format("%s:%s", keyPrefix, key);
  }

  /**
   * 获取完整key，在头上加上key前缀。之间用：分隔
   *
   * @param keys keys
   * @return 在头部追加keyPrefix的keys
   */
  private Collection<String> getFullKey(Collection<String> keys) {
    keys = keys.stream()
        .map(this::getFullKey)
        .collect(Collectors.toList());
    return keys;
  }
}
