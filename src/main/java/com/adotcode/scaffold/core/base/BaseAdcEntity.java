package com.adotcode.scaffold.core.base;

import com.adotcode.scaffold.core.handler.datatype.UUIDTypeHandler;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * 基础实体类
 *
 * @param <TPrimaryKey> TPrimaryKey为主键类型
 * @param <TAuditBy> TAuditBy为操作人的类型
 * @author risfeng
 * @date 2019/08/24
 */
@Data
public abstract class BaseAdcEntity<TPrimaryKey, TAuditBy> implements Serializable,
    Cloneable {

  private static final long serialVersionUID = -467320348822673006L;
  /**
   * 主键
   */
  @Id
  @ApiModelProperty(value = "主键", dataType = "TPrimaryKey")
  @Column(name = "id")
  @ColumnType(jdbcType = JdbcType.VARCHAR, typeHandler = UUIDTypeHandler.class)
  private TPrimaryKey id;

  /**
   * 乐观锁版本号
   */
  @ApiModelProperty(value = "乐观锁版本号", dataType = "long", example = "1")
  @Column(name = "version")
  private Long version = 1L;

  /**
   * 创建人
   */
  @ApiModelProperty(value = "创建人", dataType = "TAuditBy")
  @Column(name = "created_by")
  @ColumnType(jdbcType = JdbcType.VARCHAR, typeHandler = UUIDTypeHandler.class)
  private TAuditBy createdBy;

  /**
   * 创建时间
   */
  @ApiModelProperty(value = "创建时间", dataType = "Date", example = "2019-09-02 23:38:33")
  @Column(name = "created_at")
  private Date createdAt;

  /**
   * 更新人
   */
  @ApiModelProperty(value = "更新人", dataType = "TAuditBy")
  @Column(name = "updated_by")
  @ColumnType(jdbcType = JdbcType.VARCHAR, typeHandler = UUIDTypeHandler.class)
  private TAuditBy updatedBy;

  /**
   * 更新时间
   */
  @ApiModelProperty(value = "更新时间", dataType = "Date", example = "2019-09-02 23:38:33")
  @Column(name = "updated_at")
  private Date updatedAt;

  /**
   * 是否删除
   */
  @ApiModelProperty(value = "是否删除", dataType = "Boolean", example = "true")
  @Column(name = "deleted")
  private Boolean deleted = false;

  /**
   * 删除人
   */
  @ApiModelProperty(value = "删除人", dataType = "TAuditBy")
  @Column(name = "deleted_by")
  @ColumnType(jdbcType = JdbcType.VARCHAR, typeHandler = UUIDTypeHandler.class)
  private TAuditBy deletedBy;

  /**
   * 删除时间
   */
  @ApiModelProperty(value = "删除时间", dataType = "Date", example = "2019-09-02 23:38:33")
  @Column(name = "deleted_at")
  private Date deletedAt;

  /**
   * 设置创建信息
   *
   * @param createdBy 创建人
   */
  public void setCreatedAudit(TAuditBy createdBy) {
    this.createdBy = createdBy;
    this.createdAt = new Date();
  }

  /**
   * 设置更新信息
   *
   * @param updatedBy 更新人
   */
  public void setUpdatedAudit(TAuditBy updatedBy) {
    this.updatedBy = updatedBy;
    this.updatedAt = new Date();
  }

  /**
   * 设置删除信息
   *
   * @param deletedBy 删除人
   */
  public void setDeletedAudit(TAuditBy deletedBy) {
    this.deleted = true;
    this.deletedAt = new Date();
    this.deletedBy = deletedBy;
  }

  /**
   * 转字符串
   *
   * @return 字符串
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  /**
   * 转Json字符串
   *
   * @return json字符串
   */
  public String toJsonString() {
    return JSON.toJSONString(this);
  }
}
