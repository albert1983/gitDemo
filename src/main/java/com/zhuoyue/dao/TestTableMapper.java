package com.zhuoyue.dao;

import com.zhuoyue.bean.TestTable;

public interface TestTableMapper {
    int insert(TestTable record);

    int insertSelective(TestTable record);
}