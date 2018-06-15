package com.zhuoyue.bean.enums;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author gzd
 * @date 2017/11/3 9:43
 * @desc mybatis插入枚举值的处理器
 */
public class CityCodeEnumHandler extends BaseTypeHandler<CityCodeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, CityCodeEnum cityCodeEnum, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            preparedStatement.setString(i, String.valueOf(cityCodeEnum.getCode()));
        } else {
            preparedStatement.setObject(i, cityCodeEnum.getCode(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public CityCodeEnum getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String s = resultSet.getString(columnName);
        return s == null ? null : CityCodeEnum.toEnum(s);
    }

    @Override
    public CityCodeEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String s = resultSet.getString(i);
        return s == null ? null : CityCodeEnum.toEnum(s);
    }

    @Override
    public CityCodeEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String s = callableStatement.getString(i);
        return s == null ? null : CityCodeEnum.toEnum(s);
    }
}
