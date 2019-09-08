package com.adotcode.scaffold.core.base;

import java.util.List;

/**
 * 基础服务接口
 *
 * @param <TEntity> 实体类型
 * @param <TPrimaryKey> 主键类型
 * @author risfeng
 * @date 2019/08/25
 */
public interface BaseAdcService<TEntity, TPrimaryKey> {

  /**
   * 保存一个实体，null的属性也会保存，不会使用数据库默认值
   *
   * @param record 保存的记录
   * @return {@code TEntity} record
   */
  TEntity insert(TEntity record);

  /**
   * 批量插入，null的属性也会保存，不会使用数据库默认值
   *
   * @param recordList 保存的记录列表
   * @return {@code List<TEntity>}  record
   */
  List<TEntity> insert(List<TEntity> recordList);

  /**
   * 保存一个实体，null的属性不会保存，会使用数据库默认值
   *
   * @param record 保存的记录
   * @return {@code TEntity}  record
   */
  TEntity insertSelective(TEntity record);

  /**
   * 批量插入，null的属性不会保存，会使用数据库默认值
   *
   * @param recordList 保存的记录列表
   * @return {@code List<TEntity>} record
   */
  List<TEntity> insertSelective(List<TEntity> recordList);


  /**
   * 根据主键更新实体全部字段，null值会被更新
   *
   * @param record 保存的记录
   * @return {@code TEntity} record
   */
  TEntity update(TEntity record);

  /**
   * 批量更新，根据主键更新实体全部字段，null值会被更新
   *
   * @param recordList 保存的记录
   * @return {@code List<TEntity>} record
   */
  List<TEntity> update(List<TEntity> recordList);

  /**
   * 根据主键更新属性不为null的值
   *
   * @param record 保存的记录
   * @return {@code TEntity} record
   */
  TEntity updateSelective(TEntity record);

  /**
   * 批量更新，根据主键更新属性不为null的值
   *
   * @param recordList 保存的记录
   * @return {@code List<TEntity>} record
   */
  List<TEntity> updateSelective(List<TEntity> recordList);

  /**
   * 根据主键删除
   *
   * @param id id不能为空
   * @return 影响行数
   */
  int deleteById(TPrimaryKey id);

  /**
   * 根据主键删除多个实体，ID数组
   *
   * @param ids 类似[1,2,3]，不能为空
   * @return 影响行数
   */
  int deleteByIds(TPrimaryKey[] ids);

  /**
   * 根据实体属性作为条件进行删除
   *
   * @param record 删除的记录
   * @return 影响行数
   */
  int delete(TEntity record);

  /**
   * 根据主键删除多个实体
   *
   * @param recordList 删除的记录
   * @return 影响行数
   */
  int delete(List<TEntity> recordList);

  /**
   * 根据主键查询
   *
   * @param id 不能为空
   * @return {@code TEntity} 记录
   */
  TEntity getById(TPrimaryKey id);

  /**
   * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常
   *
   * @param record 记录
   * @return {@code TEntity} 记录
   */
  TEntity get(TEntity record);

  /**
   * 根据字段和值查询 返回一个
   *
   * @param key 字段名称
   * @param value 值
   * @return {@code TEntity} 记录
   */
  TEntity get(String key, Object value);


  /**
   * 根据主键字符串进行查询
   *
   * @param ids 如 "[1,2,3,4]"
   * @return {@code List<TEntity>} 记录
   */
  List<TEntity> selectByIds(TPrimaryKey[] ids);

  /**
   * 根据实体中的属性值进行查询
   *
   * @param record 记录
   * @return {@code List<TEntity>} 记录
   */
  List<TEntity> select(TEntity record);

  /**
   * 根据属性和值查询
   *
   * @param key 字段名称
   * @param value 值
   * @return {@code List<TEntity>} 记录
   */
  List<TEntity> select(String key, Object value);

  /**
   * 根据实体中的属性值进行分页查询
   *
   * @param record 记录
   * @param pageIndex 页码
   * @param pageSize 每页大小
   * @return {@code List<TEntity>} 记录
   */
  List<TEntity> select(TEntity record, int pageIndex, int pageSize);

  /**
   * 查询全部结果
   *
   * @return {@code List<TEntity>} 记录列表
   */
  List<TEntity> selectAll();

  /**
   * 根据实体中的属性查询总数
   *
   * @param record 记录
   * @return int 记录数
   */
  int count(TEntity record);

}
