/*
 *    Copyright 2009-2023 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.guice;

import jakarta.inject.Inject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * TypeHandler for user name.
 *
 * @author poitrac
 */
public class ContactNameTypeHandler implements TypeHandler<String> {
  private Counter counter;

  @Override
  public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
    counter.increment();
    ps.setString(i, parameter);
  }

  @Override
  public String getResult(ResultSet rs, String columnName) throws SQLException {
    counter.increment();
    String ret = rs.getString(columnName);
    if (rs.wasNull()) {
      return null;
    } else {
      return ret;
    }
  }

  @Override
  public String getResult(ResultSet rs, int columnIndex) throws SQLException {
    counter.increment();
    String ret = rs.getString(columnIndex);
    if (rs.wasNull()) {
      return null;
    } else {
      return ret;
    }
  }

  @Override
  public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
    counter.increment();
    String ret = cs.getString(columnIndex);
    if (cs.wasNull()) {
      return null;
    } else {
      return ret;
    }
  }

  @Inject
  public void setCounter(Counter counter) {
    this.counter = counter;
  }
}
