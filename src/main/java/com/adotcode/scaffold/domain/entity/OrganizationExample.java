package com.adotcode.scaffold.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 组织机构树查询条件
 *
 * @author risfeng
 * @date 2019/09/02
 */
public class OrganizationExample {

  private String orderByClause;

  private boolean distinct;

  /**
   * 逻辑或
   */

  List<CriteriaBase> oredCriteria;

  public OrganizationExample() {
    oredCriteria = new ArrayList<>();
  }

  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  public String getOrderByClause() {
    return orderByClause;
  }

  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  public boolean isDistinct() {
    return distinct;
  }

  public List<CriteriaBase> getOredCriteria() {
    return oredCriteria;
  }

  public void or(CriteriaBase criteria) {
    oredCriteria.add(criteria);
  }

  public CriteriaBase or() {
    CriteriaBase criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  public CriteriaBase createCriteria() {
    CriteriaBase criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  private CriteriaBase createCriteriaInternal() {
    return new CriteriaBase();
  }

  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  private abstract static class BaseGeneratedCriteria {

    private List<Criterion> criteria;

    private BaseGeneratedCriteria() {
      super();
      criteria = new ArrayList<>();
    }

    public boolean isValid() {
      return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
      return criteria;
    }

    public List<Criterion> getCriteria() {
      return criteria;
    }

    private void addCriterion(String condition) {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      criteria.add(new Criterion(condition));
    }

    private void addCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value));
    }

    private void addCriterion(String condition, Object value1, Object value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value1, value2));
    }

    public CriteriaBase andIdIsNull() {
      addCriterion("id is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdIsNotNull() {
      addCriterion("id is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdEqualTo(String value) {
      addCriterion("id =", value, "id");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdNotEqualTo(String value) {
      addCriterion("id <>", value, "id");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdGreaterThan(String value) {
      addCriterion("id >", value, "id");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdGreaterThanOrEqualTo(String value) {
      addCriterion("id >=", value, "id");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdLessThan(String value) {
      addCriterion("id <", value, "id");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdLessThanOrEqualTo(String value) {
      addCriterion("id <=", value, "id");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdLike(String value) {
      addCriterion("id like", value, "id");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdNotLike(String value) {
      addCriterion("id not like", value, "id");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdIn(List<String> values) {
      addCriterion("id in", values, "id");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdNotIn(List<String> values) {
      addCriterion("id not in", values, "id");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdBetween(String value1, String value2) {
      addCriterion("id between", value1, value2, "id");
      return (CriteriaBase) this;
    }

    public CriteriaBase andIdNotBetween(String value1, String value2) {
      addCriterion("id not between", value1, value2, "id");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeIsNull() {
      addCriterion("code is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeIsNotNull() {
      addCriterion("code is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeEqualTo(String value) {
      addCriterion("code =", value, "code");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeNotEqualTo(String value) {
      addCriterion("code <>", value, "code");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeGreaterThan(String value) {
      addCriterion("code >", value, "code");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeGreaterThanOrEqualTo(String value) {
      addCriterion("code >=", value, "code");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeLessThan(String value) {
      addCriterion("code <", value, "code");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeLessThanOrEqualTo(String value) {
      addCriterion("code <=", value, "code");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeLike(String value) {
      addCriterion("code like", value, "code");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeNotLike(String value) {
      addCriterion("code not like", value, "code");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeIn(List<String> values) {
      addCriterion("code in", values, "code");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeNotIn(List<String> values) {
      addCriterion("code not in", values, "code");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeBetween(String value1, String value2) {
      addCriterion("code between", value1, value2, "code");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCodeNotBetween(String value1, String value2) {
      addCriterion("code not between", value1, value2, "code");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameIsNull() {
      addCriterion("`name` is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameIsNotNull() {
      addCriterion("`name` is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameEqualTo(String value) {
      addCriterion("`name` =", value, "name");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameNotEqualTo(String value) {
      addCriterion("`name` <>", value, "name");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameGreaterThan(String value) {
      addCriterion("`name` >", value, "name");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameGreaterThanOrEqualTo(String value) {
      addCriterion("`name` >=", value, "name");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameLessThan(String value) {
      addCriterion("`name` <", value, "name");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameLessThanOrEqualTo(String value) {
      addCriterion("`name` <=", value, "name");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameLike(String value) {
      addCriterion("`name` like", value, "name");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameNotLike(String value) {
      addCriterion("`name` not like", value, "name");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameIn(List<String> values) {
      addCriterion("`name` in", values, "name");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameNotIn(List<String> values) {
      addCriterion("`name` not in", values, "name");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameBetween(String value1, String value2) {
      addCriterion("`name` between", value1, value2, "name");
      return (CriteriaBase) this;
    }

    public CriteriaBase andNameNotBetween(String value1, String value2) {
      addCriterion("`name` not between", value1, value2, "name");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdIsNull() {
      addCriterion("parent_id is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdIsNotNull() {
      addCriterion("parent_id is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdEqualTo(String value) {
      addCriterion("parent_id =", value, "parentId");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdNotEqualTo(String value) {
      addCriterion("parent_id <>", value, "parentId");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdGreaterThan(String value) {
      addCriterion("parent_id >", value, "parentId");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdGreaterThanOrEqualTo(String value) {
      addCriterion("parent_id >=", value, "parentId");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdLessThan(String value) {
      addCriterion("parent_id <", value, "parentId");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdLessThanOrEqualTo(String value) {
      addCriterion("parent_id <=", value, "parentId");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdLike(String value) {
      addCriterion("parent_id like", value, "parentId");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdNotLike(String value) {
      addCriterion("parent_id not like", value, "parentId");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdIn(List<String> values) {
      addCriterion("parent_id in", values, "parentId");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdNotIn(List<String> values) {
      addCriterion("parent_id not in", values, "parentId");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdBetween(String value1, String value2) {
      addCriterion("parent_id between", value1, value2, "parentId");
      return (CriteriaBase) this;
    }

    public CriteriaBase andParentIdNotBetween(String value1, String value2) {
      addCriterion("parent_id not between", value1, value2, "parentId");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLeafIsNull() {
      addCriterion("leaf is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLeafIsNotNull() {
      addCriterion("leaf is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLeafEqualTo(Boolean value) {
      addCriterion("leaf =", value, "leaf");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLeafNotEqualTo(Boolean value) {
      addCriterion("leaf <>", value, "leaf");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLeafGreaterThan(Boolean value) {
      addCriterion("leaf >", value, "leaf");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLeafGreaterThanOrEqualTo(Boolean value) {
      addCriterion("leaf >=", value, "leaf");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLeafLessThan(Boolean value) {
      addCriterion("leaf <", value, "leaf");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLeafLessThanOrEqualTo(Boolean value) {
      addCriterion("leaf <=", value, "leaf");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLeafIn(List<Boolean> values) {
      addCriterion("leaf in", values, "leaf");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLeafNotIn(List<Boolean> values) {
      addCriterion("leaf not in", values, "leaf");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLeafBetween(Boolean value1, Boolean value2) {
      addCriterion("leaf between", value1, value2, "leaf");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLeafNotBetween(Boolean value1, Boolean value2) {
      addCriterion("leaf not between", value1, value2, "leaf");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLevelIsNull() {
      addCriterion("`level` is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLevelIsNotNull() {
      addCriterion("`level` is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLevelEqualTo(Integer value) {
      addCriterion("`level` =", value, "level");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLevelNotEqualTo(Integer value) {
      addCriterion("`level` <>", value, "level");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLevelGreaterThan(Integer value) {
      addCriterion("`level` >", value, "level");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLevelGreaterThanOrEqualTo(Integer value) {
      addCriterion("`level` >=", value, "level");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLevelLessThan(Integer value) {
      addCriterion("`level` <", value, "level");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLevelLessThanOrEqualTo(Integer value) {
      addCriterion("`level` <=", value, "level");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLevelIn(List<Integer> values) {
      addCriterion("`level` in", values, "level");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLevelNotIn(List<Integer> values) {
      addCriterion("`level` not in", values, "level");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLevelBetween(Integer value1, Integer value2) {
      addCriterion("`level` between", value1, value2, "level");
      return (CriteriaBase) this;
    }

    public CriteriaBase andLevelNotBetween(Integer value1, Integer value2) {
      addCriterion("`level` not between", value1, value2, "level");
      return (CriteriaBase) this;
    }

    public CriteriaBase andVersionIsNull() {
      addCriterion("version is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andVersionIsNotNull() {
      addCriterion("version is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andVersionEqualTo(Long value) {
      addCriterion("version =", value, "version");
      return (CriteriaBase) this;
    }

    public CriteriaBase andVersionNotEqualTo(Long value) {
      addCriterion("version <>", value, "version");
      return (CriteriaBase) this;
    }

    public CriteriaBase andVersionGreaterThan(Long value) {
      addCriterion("version >", value, "version");
      return (CriteriaBase) this;
    }

    public CriteriaBase andVersionGreaterThanOrEqualTo(Long value) {
      addCriterion("version >=", value, "version");
      return (CriteriaBase) this;
    }

    public CriteriaBase andVersionLessThan(Long value) {
      addCriterion("version <", value, "version");
      return (CriteriaBase) this;
    }

    public CriteriaBase andVersionLessThanOrEqualTo(Long value) {
      addCriterion("version <=", value, "version");
      return (CriteriaBase) this;
    }

    public CriteriaBase andVersionIn(List<Long> values) {
      addCriterion("version in", values, "version");
      return (CriteriaBase) this;
    }

    public CriteriaBase andVersionNotIn(List<Long> values) {
      addCriterion("version not in", values, "version");
      return (CriteriaBase) this;
    }

    public CriteriaBase andVersionBetween(Long value1, Long value2) {
      addCriterion("version between", value1, value2, "version");
      return (CriteriaBase) this;
    }

    public CriteriaBase andVersionNotBetween(Long value1, Long value2) {
      addCriterion("version not between", value1, value2, "version");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByIsNull() {
      addCriterion("created_by is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByIsNotNull() {
      addCriterion("created_by is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByEqualTo(String value) {
      addCriterion("created_by =", value, "createdBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByNotEqualTo(String value) {
      addCriterion("created_by <>", value, "createdBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByGreaterThan(String value) {
      addCriterion("created_by >", value, "createdBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByGreaterThanOrEqualTo(String value) {
      addCriterion("created_by >=", value, "createdBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByLessThan(String value) {
      addCriterion("created_by <", value, "createdBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByLessThanOrEqualTo(String value) {
      addCriterion("created_by <=", value, "createdBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByLike(String value) {
      addCriterion("created_by like", value, "createdBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByNotLike(String value) {
      addCriterion("created_by not like", value, "createdBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByIn(List<String> values) {
      addCriterion("created_by in", values, "createdBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByNotIn(List<String> values) {
      addCriterion("created_by not in", values, "createdBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByBetween(String value1, String value2) {
      addCriterion("created_by between", value1, value2, "createdBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedByNotBetween(String value1, String value2) {
      addCriterion("created_by not between", value1, value2, "createdBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedAtIsNull() {
      addCriterion("created_at is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedAtIsNotNull() {
      addCriterion("created_at is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedAtEqualTo(Date value) {
      addCriterion("created_at =", value, "createdAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedAtNotEqualTo(Date value) {
      addCriterion("created_at <>", value, "createdAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedAtGreaterThan(Date value) {
      addCriterion("created_at >", value, "createdAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedAtGreaterThanOrEqualTo(Date value) {
      addCriterion("created_at >=", value, "createdAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedAtLessThan(Date value) {
      addCriterion("created_at <", value, "createdAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedAtLessThanOrEqualTo(Date value) {
      addCriterion("created_at <=", value, "createdAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedAtIn(List<Date> values) {
      addCriterion("created_at in", values, "createdAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedAtNotIn(List<Date> values) {
      addCriterion("created_at not in", values, "createdAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedAtBetween(Date value1, Date value2) {
      addCriterion("created_at between", value1, value2, "createdAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andCreatedAtNotBetween(Date value1, Date value2) {
      addCriterion("created_at not between", value1, value2, "createdAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByIsNull() {
      addCriterion("updated_by is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByIsNotNull() {
      addCriterion("updated_by is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByEqualTo(String value) {
      addCriterion("updated_by =", value, "updatedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByNotEqualTo(String value) {
      addCriterion("updated_by <>", value, "updatedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByGreaterThan(String value) {
      addCriterion("updated_by >", value, "updatedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByGreaterThanOrEqualTo(String value) {
      addCriterion("updated_by >=", value, "updatedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByLessThan(String value) {
      addCriterion("updated_by <", value, "updatedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByLessThanOrEqualTo(String value) {
      addCriterion("updated_by <=", value, "updatedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByLike(String value) {
      addCriterion("updated_by like", value, "updatedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByNotLike(String value) {
      addCriterion("updated_by not like", value, "updatedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByIn(List<String> values) {
      addCriterion("updated_by in", values, "updatedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByNotIn(List<String> values) {
      addCriterion("updated_by not in", values, "updatedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByBetween(String value1, String value2) {
      addCriterion("updated_by between", value1, value2, "updatedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedByNotBetween(String value1, String value2) {
      addCriterion("updated_by not between", value1, value2, "updatedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedAtIsNull() {
      addCriterion("updated_at is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedAtIsNotNull() {
      addCriterion("updated_at is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedAtEqualTo(Date value) {
      addCriterion("updated_at =", value, "updatedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedAtNotEqualTo(Date value) {
      addCriterion("updated_at <>", value, "updatedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedAtGreaterThan(Date value) {
      addCriterion("updated_at >", value, "updatedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedAtGreaterThanOrEqualTo(Date value) {
      addCriterion("updated_at >=", value, "updatedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedAtLessThan(Date value) {
      addCriterion("updated_at <", value, "updatedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedAtLessThanOrEqualTo(Date value) {
      addCriterion("updated_at <=", value, "updatedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedAtIn(List<Date> values) {
      addCriterion("updated_at in", values, "updatedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedAtNotIn(List<Date> values) {
      addCriterion("updated_at not in", values, "updatedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedAtBetween(Date value1, Date value2) {
      addCriterion("updated_at between", value1, value2, "updatedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andUpdatedAtNotBetween(Date value1, Date value2) {
      addCriterion("updated_at not between", value1, value2, "updatedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedIsNull() {
      addCriterion("deleted is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedIsNotNull() {
      addCriterion("deleted is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedEqualTo(Boolean value) {
      addCriterion("deleted =", value, "deleted");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedNotEqualTo(Boolean value) {
      addCriterion("deleted <>", value, "deleted");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedGreaterThan(Boolean value) {
      addCriterion("deleted >", value, "deleted");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedGreaterThanOrEqualTo(Boolean value) {
      addCriterion("deleted >=", value, "deleted");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedLessThan(Boolean value) {
      addCriterion("deleted <", value, "deleted");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedLessThanOrEqualTo(Boolean value) {
      addCriterion("deleted <=", value, "deleted");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedIn(List<Boolean> values) {
      addCriterion("deleted in", values, "deleted");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedNotIn(List<Boolean> values) {
      addCriterion("deleted not in", values, "deleted");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedBetween(Boolean value1, Boolean value2) {
      addCriterion("deleted between", value1, value2, "deleted");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedNotBetween(Boolean value1, Boolean value2) {
      addCriterion("deleted not between", value1, value2, "deleted");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByIsNull() {
      addCriterion("deleted_by is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByIsNotNull() {
      addCriterion("deleted_by is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByEqualTo(String value) {
      addCriterion("deleted_by =", value, "deletedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByNotEqualTo(String value) {
      addCriterion("deleted_by <>", value, "deletedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByGreaterThan(String value) {
      addCriterion("deleted_by >", value, "deletedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByGreaterThanOrEqualTo(String value) {
      addCriterion("deleted_by >=", value, "deletedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByLessThan(String value) {
      addCriterion("deleted_by <", value, "deletedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByLessThanOrEqualTo(String value) {
      addCriterion("deleted_by <=", value, "deletedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByLike(String value) {
      addCriterion("deleted_by like", value, "deletedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByNotLike(String value) {
      addCriterion("deleted_by not like", value, "deletedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByIn(List<String> values) {
      addCriterion("deleted_by in", values, "deletedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByNotIn(List<String> values) {
      addCriterion("deleted_by not in", values, "deletedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByBetween(String value1, String value2) {
      addCriterion("deleted_by between", value1, value2, "deletedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedByNotBetween(String value1, String value2) {
      addCriterion("deleted_by not between", value1, value2, "deletedBy");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedAtIsNull() {
      addCriterion("deleted_at is null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedAtIsNotNull() {
      addCriterion("deleted_at is not null");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedAtEqualTo(Date value) {
      addCriterion("deleted_at =", value, "deletedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedAtNotEqualTo(Date value) {
      addCriterion("deleted_at <>", value, "deletedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedAtGreaterThan(Date value) {
      addCriterion("deleted_at >", value, "deletedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedAtGreaterThanOrEqualTo(Date value) {
      addCriterion("deleted_at >=", value, "deletedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedAtLessThan(Date value) {
      addCriterion("deleted_at <", value, "deletedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedAtLessThanOrEqualTo(Date value) {
      addCriterion("deleted_at <=", value, "deletedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedAtIn(List<Date> values) {
      addCriterion("deleted_at in", values, "deletedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedAtNotIn(List<Date> values) {
      addCriterion("deleted_at not in", values, "deletedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedAtBetween(Date value1, Date value2) {
      addCriterion("deleted_at between", value1, value2, "deletedAt");
      return (CriteriaBase) this;
    }

    public CriteriaBase andDeletedAtNotBetween(Date value1, Date value2) {
      addCriterion("deleted_at not between", value1, value2, "deletedAt");
      return (CriteriaBase) this;
    }
  }

  public static class CriteriaBase extends BaseGeneratedCriteria {

    private CriteriaBase() {
      super();
    }
  }

  public static class Criterion {

    private String condition;

    private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    private String typeHandler;

    public String getCondition() {
      return condition;
    }

    public Object getValue() {
      return value;
    }

    public Object getSecondValue() {
      return secondValue;
    }

    public boolean isNoValue() {
      return noValue;
    }

    public boolean isSingleValue() {
      return singleValue;
    }

    public boolean isBetweenValue() {
      return betweenValue;
    }

    public boolean isListValue() {
      return listValue;
    }

    public String getTypeHandler() {
      return typeHandler;
    }

    private Criterion(String condition) {
      super();
      this.condition = condition;
      this.typeHandler = null;
      this.noValue = true;
    }

    private Criterion(String condition, Object value, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.typeHandler = typeHandler;
      if (value instanceof List<?>) {
        this.listValue = true;
      } else {
        this.singleValue = true;
      }
    }

    private Criterion(String condition, Object value) {
      this(condition, value, null);
    }

    private Criterion(String condition, Object value, Object secondValue, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.secondValue = secondValue;
      this.typeHandler = typeHandler;
      this.betweenValue = true;
    }

    private Criterion(String condition, Object value, Object secondValue) {
      this(condition, value, secondValue, null);
    }
  }
}