package com.adotcode.scaffold.core.base;

import com.adotcode.scaffold.core.exception.application.OptimisticLockException;
import com.adotcode.scaffold.core.util.reflection.ReflectionUtils;
import com.github.pagehelper.PageHelper;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基础服务接口实现
 *
 * @param <TEntity> 实体类型
 * @param <TPrimaryKey> 主键类型
 * @author risfeng
 * @date 2019/08/25
 */
@Slf4j
public abstract class BaseAdcServiceImpl<TEntity, TPrimaryKey> implements
    BaseAdcService<TEntity, TPrimaryKey> {

  /**
   * 自定义基础Mapper
   */
  @Resource
  private Mapper<TEntity> mapper;

  /**
   * TEntity 类型
   */
  private Class<? extends TEntity> entityClass;

  @SuppressWarnings("unchecked")
  @PostConstruct
  public void init() {
    this.entityClass = ReflectionUtils.getClassGenericType(getClass());
  }

  /**
   * 保存一个实体，null的属性也会保存，不会使用数据库默认值
   *
   * @param record 保存的记录
   * @return {@code TEntity} record
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public TEntity insert(TEntity record) {
    mapper.insert(record);
    return record;
  }

  /**
   * 批量插入，null的属性也会保存，不会使用数据库默认值
   *
   * @param recordList 保存的记录列表
   * @return {@code List<TEntity>}  record
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public List<TEntity> insert(List<TEntity> recordList) {
    mapper.insertList(recordList);
    return recordList;
  }

  /**
   * 保存一个实体，null的属性不会保存，会使用数据库默认值
   *
   * @param record 保存的记录
   * @return {@code TEntity}  record
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public TEntity insertSelective(TEntity record) {
    mapper.insertSelective(record);
    return record;
  }

  /**
   * 批量插入，null的属性不会保存，会使用数据库默认值
   *
   * @param recordList 保存的记录列表
   * @return {@code List<TEntity>} record
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public List<TEntity> insertSelective(List<TEntity> recordList) {
    for (TEntity record : recordList) {
      mapper.insertSelective(record);
    }
    return recordList;
  }

  /**
   * 根据主键更新实体全部字段，null值会被更新
   *
   * @param record 保存的记录
   * @return {@code TEntity} record
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public TEntity update(TEntity record) {
    int count = mapper.updateByPrimaryKey(record);
    //更新失败乐观锁检测
    checkOptimisticLock(count, record);
    return record;
  }

  /**
   * 批量更新，根据主键更新实体全部字段，null值会被更新
   *
   * @param recordList 保存的记录
   * @return {@code List<TEntity>} record
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public List<TEntity> update(List<TEntity> recordList) {
    for (TEntity record : recordList) {
      int count = mapper.updateByPrimaryKey(record);
      checkOptimisticLock(count, record);
    }
    return recordList;
  }

  /**
   * 根据主键更新属性不为null的值
   *
   * @param record 保存的记录
   * @return {@code TEntity} record
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public TEntity updateSelective(TEntity record) {
    int count = mapper.updateByPrimaryKeySelective(record);
    checkOptimisticLock(count, record);
    return record;
  }

  /**
   * 批量更新，根据主键更新属性不为null的值
   *
   * @param recordList 保存的记录
   * @return {@code List<TEntity>} record
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public List<TEntity> updateSelective(List<TEntity> recordList) {
    for (TEntity record : recordList) {
      int count = mapper.updateByPrimaryKeySelective(record);
      checkOptimisticLock(count, record);
    }
    return recordList;
  }

  /**
   * 根据主键删除
   *
   * @param id id不能为空
   * @return 影响行数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int deleteById(TPrimaryKey id) {
    return mapper.deleteByPrimaryKey(id);
  }

  /**
   * 根据主键删除多个实体，ID数组
   *
   * @param ids 类似[1,2,3]，不能为空
   * @return 影响行数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int deleteByIds(TPrimaryKey[] ids) {
    int count = 0;
    for (TPrimaryKey id : ids) {
      mapper.deleteByPrimaryKey(id);
      count++;
    }
    return count;
  }

  /**
   * 根据实体属性作为条件进行删除
   *
   * @param record 删除的记录
   * @return 影响行数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int delete(TEntity record) {
    return mapper.delete(record);
  }

  /**
   * 根据主键删除多个实体
   *
   * @param recordList 删除的记录
   * @return 影响行数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public int delete(List<TEntity> recordList) {
    int count = 0;
    for (TEntity record : recordList) {
      mapper.delete(record);
      count++;
    }
    return count;
  }

  /**
   * 根据主键查询
   *
   * @param id 不能为空
   * @return {@code TEntity} 记录
   */
  @Override
  public TEntity getById(TPrimaryKey id) {
    return mapper.selectByPrimaryKey(id);
  }

  /**
   * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常
   *
   * @param record 记录
   * @return {@code TEntity} 记录
   */
  @Override
  public TEntity get(TEntity record) {
    return mapper.selectOne(record);
  }

  /**
   * 根据字段和值查询 返回一个
   *
   * @param key 字段名称
   * @param value 值
   * @return {@code TEntity} 记录
   */
  @Override
  public TEntity get(String key, Object value) {
    TEntity entity = null;
    try {
      entity = entityClass.getDeclaredConstructor().newInstance();
      Field field = ReflectionUtils.getField(entityClass, key);
      assert field != null;
      field.set(entity, value);
    } catch (Exception e) {
      log.error("find TEntity type by key:[{}] and value:[{}] error.", key, value, e);
    }
    return mapper.selectOne(entity);
  }

  /**
   * 根据主键字符串进行查询
   *
   * @param ids 如 "[1,2,3,4]"
   * @return {@code List<TEntity>} 记录
   */
  @Override
  public List<TEntity> selectByIds(TPrimaryKey[] ids) {
    //todo
    return mapper.selectByIds(Arrays.toString(ids));
  }

  /**
   * 根据实体中的属性值进行查询
   *
   * @param record 记录
   * @return {@code List<TEntity>} 记录
   */
  @Override
  public List<TEntity> select(TEntity record) {
    return mapper.select(record);
  }

  /**
   * 根据属性和值查询
   *
   * @param key 字段名称
   * @param value 值
   * @return {@code List<TEntity>} 记录
   */
  @Override
  public List<TEntity> select(String key, Object value) {
    TEntity entity = null;
    try {
      entity = entityClass.getDeclaredConstructor().newInstance();
      Field field = ReflectionUtils.getField(entityClass, key);
      assert field != null;
      field.set(entity, value);
    } catch (Exception e) {
      log.error("find TEntity type by key:[{}] and value:[{}] error.", key, value, e);
    }
    return mapper.select(entity);
  }

  /**
   * 根据实体中的属性值进行分页查询
   *
   * @param record 记录
   * @param pageIndex 页码
   * @param pageSize 每页大小
   * @return {@code List<TEntity>} 记录
   */
  @Override
  public List<TEntity> select(TEntity record, int pageIndex, int pageSize) {
    PageHelper.startPage(pageIndex, pageSize);
    return mapper.select(record);
  }

  /**
   * 查询全部结果
   *
   * @return {@code List<TEntity>} 记录列表
   */
  @Override
  public List<TEntity> selectAll() {
    return mapper.selectAll();
  }

  /**
   * 根据实体中的属性查询总数
   *
   * @param record 记录
   * @return int 记录数
   */
  @Override
  public int count(TEntity record) {
    return mapper.selectCount(record);
  }

  /**
   * 检查乐观锁
   * <p>
   * 更新失败时，抛出OptimisticLockException异常
   * <p>
   * 配合{@code RetryOnFailure}注解实失败重试
   *
   * @param updateCount update,delete 操作返回的影响行数
   * @param record 操作记录
   */
  private void checkOptimisticLock(int updateCount, TEntity record) {
    if (updateCount == 0 && record instanceof BaseAdcEntity) {
      BaseAdcEntity baseEntity = (BaseAdcEntity) record;
      if (baseEntity.getVersion() > 0) {
        throw new OptimisticLockException();
      }
    }
  }
}
