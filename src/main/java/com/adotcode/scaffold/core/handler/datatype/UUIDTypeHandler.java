package com.adotcode.scaffold.core.handler.datatype;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * UUID类型处理器
 *
 * @author risfeng
 * @date 2019/09/04
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(UUID.class)
public class UUIDTypeHandler extends BaseTypeHandler<UUID> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setString(i, parameter.toString());
  }

  @Override
  public UUID getNullableResult(ResultSet rs, String columnName)
      throws SQLException {
    String uuid = rs.getString(columnName);
    if (StringUtils.isBlank(uuid)) {
      return null;
    }
    return UUID.fromString(uuid);
  }

  @Override
  public UUID getNullableResult(ResultSet rs, int columnIndex)
      throws SQLException {
    String uuid = rs.getString(columnIndex);
    if (StringUtils.isBlank(uuid)) {
      return null;
    }
    return UUID.fromString(uuid);
  }

  @Override
  public UUID getNullableResult(CallableStatement cs, int columnIndex)
      throws SQLException {
    String uuid = cs.getString(columnIndex);
    if (StringUtils.isBlank(uuid)) {
      return null;
    }
    return UUID.fromString(uuid);
  }
}
