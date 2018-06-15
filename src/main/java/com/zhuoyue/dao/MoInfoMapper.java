package com.zhuoyue.dao;

import com.zhuoyue.bean.MoInfo;

public interface MoInfoMapper {


    int insert(MoInfo record);

    int insertSelective(MoInfo record);
}