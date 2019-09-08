package com.adotcode.scaffold.core.base;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.RowBoundsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 基础Mapper接口
 * <p>
 * more: https://gitee.com/free/Mapper/tree/master/base
 * </p>
 *
 * @author risfeng
 * @date 2019/08/25
 */
public interface Mapper<TEntity> extends
    BaseMapper<TEntity>,
    ConditionMapper<TEntity>,
    IdsMapper<TEntity>,
    InsertListMapper<TEntity>,
    ExampleMapper<TEntity>,
    RowBoundsMapper<TEntity> {

}
