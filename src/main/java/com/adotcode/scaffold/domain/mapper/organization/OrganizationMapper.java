package com.adotcode.scaffold.domain.mapper.organization;

import com.adotcode.scaffold.core.base.Mapper;
import com.adotcode.scaffold.domain.entity.Organization;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * ${description}
 *
 * @author risfeng
 * @date 2019/09/04
 */
@Repository
public interface OrganizationMapper extends
    Mapper<Organization> {

  /**
   * 批量更新
   *
   * @param list 更新列表
   * @return 影响行数
   */
  int updateBatch(List<Organization> list);

  /**
   * 批量更新
   *
   * @param list 更新列表
   * @return 影响行数
   */
  int batchInsert(List<Organization> list);

  /**
   * 插入或更新
   *
   * @param record 更新列表
   * @return 影响行数
   */
  int insertOrUpdate(Organization record);

  /**
   * 选择性插入或更新（null不处理）
   *
   * @param record 更新列表
   * @return 影响行数
   */
  int insertOrUpdateSelective(Organization record);
}