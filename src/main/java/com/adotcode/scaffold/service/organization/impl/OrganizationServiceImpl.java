package com.adotcode.scaffold.service.organization.impl;

import com.adotcode.scaffold.core.base.BaseAdcServiceImpl;
import com.adotcode.scaffold.domain.entity.Organization;
import com.adotcode.scaffold.domain.mapper.organization.OrganizationMapper;
import com.adotcode.scaffold.service.organization.OrganizationService;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 组织机构树服务接口实现
 *
 * @author risfeng
 * @date 2019/09/03
 */
@Service
public class OrganizationServiceImpl extends BaseAdcServiceImpl<Organization, UUID> implements
    OrganizationService {

  @Resource
  private OrganizationMapper organizationMapper;

  @Override
  public int updateBatch(List<Organization> list) {
    return organizationMapper.updateBatch(list);
  }

  @Override
  public int batchInsert(List<Organization> list) {
    return organizationMapper.batchInsert(list);
  }

  @Override
  public int insertOrUpdate(Organization record) {
    return organizationMapper.insertOrUpdate(record);
  }

  @Override
  public int insertOrUpdateSelective(Organization record) {
    return organizationMapper.insertOrUpdateSelective(record);
  }

//  @Override
//  public int insertSelective(Organization record) {
//    return organizationMapper.insertSelective(record);
//  }

}

