package com.adotcode.scaffold.facade.endpoint;

import com.adotcode.scaffold.core.cache.RedisService;
import com.adotcode.scaffold.core.exception.application.GenericException;
import com.adotcode.scaffold.core.exception.application.IllegalParameterException;
import com.adotcode.scaffold.core.exception.application.NullOrEmptyException;
import com.adotcode.scaffold.core.exception.application.UnAuthorizedException;
import com.adotcode.scaffold.core.response.HttpResult;
import com.adotcode.scaffold.domain.entity.Organization;
import com.adotcode.scaffold.service.auditlog.AuditLogService;
import com.adotcode.scaffold.service.organization.OrganizationService;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 审计日志控制器[测试控制器]
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-15
 */
@RequestMapping("api/v1/auditlogs")
@RestController
@Slf4j
@Validated
public class AuditLogEndPoint {


  private final AuditLogService auditLogService;

  private final OrganizationService organizationService;

  private final RedisService<String, Organization> redisService;

  public AuditLogEndPoint(
      AuditLogService auditLogService,
      OrganizationService organizationService,
      RedisService<String, Organization> redisService) {
    this.auditLogService = auditLogService;
    this.organizationService = organizationService;
    this.redisService = redisService;
  }


  /**
   * 通过Id查找浏览器信息
   *
   * @param id 日志Id
   * @return 浏览器信息 @Min(value = 2, message = "id最小值为2。")
   */
  @GetMapping("{id}/browserInfo")
  public HttpResult<String> findBrowserInfoById(
      @Min(value = -2, message = "id最小值为-2。") @PathVariable("id") long id) {
    if (id == -1) {
      throw new NullOrEmptyException();
    }
    if (id == 0) {
      throw new IllegalParameterException("id");
    }
    if (id == 1) {
      throw new UnAuthorizedException();
    }
    if (id == 2) {
      throw new GenericException("通用异常");
    }
    String result = auditLogService.findBrowserInfoById(id);
    return HttpResult.ok(result);
  }

  @GetMapping("{id}/user-info")
  public HttpResult<String> findUserInfoById(
      @Min(value = 1, message = "id最小值为1。") @PathVariable("id") long id) {
    String result = auditLogService.findUserEmailById(id);
    return HttpResult.ok(result);
  }

  /**
   * 测试
   */
  @GetMapping("org/add")
  public HttpResult<Organization> orgAdd() {
    Organization add = new Organization();
    add.setId(UUID.randomUUID());
    add.setCode("0000" + RandomUtils.nextInt());
    add.setLeaf(true);
    add.setLevel(1);
    add.setName("测试新增");
    add.setParentId(null);
    add.setCreatedAudit(UUID.randomUUID());
    add.setVersion(1L);
    Organization result = organizationService.insertSelective(add);
    return HttpResult.ok(result);
  }

  /**
   * 测试
   */
  @GetMapping("org/{id}")
  public HttpResult<Organization> getOrgById(@PathVariable UUID id) {
    String key = redisService.getKey("getOrganization", id.toString());
    Organization result = redisService.getValue(key);
    if (ObjectUtils.isNotEmpty(result)) {
      return HttpResult.ok(result);
    }
    result = organizationService.getById(id);
    redisService.setValue(key, result, 10, TimeUnit.MINUTES);
    return HttpResult.ok(result);
  }
}
