package com.adotcode.scaffold.service.organization;

import com.adotcode.scaffold.core.base.BaseAdcService;
import com.adotcode.scaffold.domain.entity.Organization;
import java.util.List;
import java.util.UUID;

/**
 * 组织机构树服务接口
 *
 * @author risfeng
 * @date 2019/09/03
 */
public interface OrganizationService extends BaseAdcService<Organization, UUID> {

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

