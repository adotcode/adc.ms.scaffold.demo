package com.adotcode.scaffold.service.auditlog.impl;

import com.adotcode.scaffold.core.annotation.SwitchDataSource;
import com.adotcode.scaffold.core.database.DataSourceTypeEnum;
import com.adotcode.scaffold.domain.mapper.log.AuditLogMapper;
import com.adotcode.scaffold.domain.mapper.user.UserMapper;
import com.adotcode.scaffold.service.auditlog.AuditLogService;
import org.springframework.stereotype.Service;

/**
 * 审计日志服务实现
 *
 * @author risfeng
 * @date 2019/08/11
 */
@Service
public class AuditLogServiceImpl implements AuditLogService {

  private final AuditLogMapper auditLogMapper;
  private final UserMapper userMapper;

  public AuditLogServiceImpl(
      AuditLogMapper auditLogMapper,
      UserMapper userMapper) {
    this.auditLogMapper = auditLogMapper;
    this.userMapper = userMapper;
  }

  /**
   * 通过Id获取浏览器信息
   *
   * @param id 日志Id
   * @return 浏览器信息
   */
  @Override
  public String findBrowserInfoById(long id) {
    return auditLogMapper.findBrowserInfoById(id);
  }

  /**
   * 通过用户Id获取邮箱信息
   *
   * @param id 用户Id
   * @return 邮箱信息
   */
  @SwitchDataSource(value = DataSourceTypeEnum.SLAVE)
  @Override
  public String findUserEmailById(long id) {
    return userMapper.findUserEmailById(id);
  }
}
