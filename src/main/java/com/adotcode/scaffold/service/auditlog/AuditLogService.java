package com.adotcode.scaffold.service.auditlog;

/**
 * 审计日志服务
 *
 * @author risfeng
 * @date 2019/08/11
 */
public interface AuditLogService {

  /**
   * 通过Id获取浏览器信息
   *
   * @param id 日志Id
   * @return 浏览器信息
   */
  String findBrowserInfoById(long id);

  /**
   * 通过用户Id获取邮箱信息
   *
   * @param id 用户Id
   * @return 邮箱信息
   */
  String findUserEmailById(long id);
}
