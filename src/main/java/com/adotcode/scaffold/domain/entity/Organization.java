package com.adotcode.scaffold.domain.entity;

import com.adotcode.scaffold.core.base.BaseAdcEntity;
import com.adotcode.scaffold.core.handler.datatype.UUIDTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * 组织机构树
 *
 * @author risfeng
 * @date 2019/09/04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "组织机构树")
@Table(name = "adc_organization")
public class Organization extends BaseAdcEntity<UUID, UUID> implements Serializable, Cloneable {

  private static final long serialVersionUID = 1108999102934174844L;

  /**
   * 路径编码
   */
  @Column(name = "code")
  @ApiModelProperty(value = "路径编码")
  private String code;

  /**
   * 名称
   */
  @Column(name = "`name`")
  @ApiModelProperty(value = "名称")
  private String name;

  /**
   * 父组织机构Id
   */
  @Column(name = "parent_id")
  @ApiModelProperty(value = "父组织机构Id")
  @ColumnType(jdbcType = JdbcType.VARCHAR, typeHandler = UUIDTypeHandler.class)
  private UUID parentId;

  /**
   * 是否叶子节点
   */
  @Column(name = "leaf")
  @ApiModelProperty(value = "是否叶子节点")
  private Boolean leaf;

  /**
   * 层级
   */
  @Column(name = "`level`")
  @ApiModelProperty(value = "层级")
  private Integer level;

}