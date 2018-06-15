package com.zhuoyue.dao;

import com.zhuoyue.bean.MoManagerType;

import java.util.List;


public interface MoManagerTypeMapper {


    int insert(MoManagerType record);

    int insertSelective(MoManagerType record);


    /**
     * 获取管理类型列表成功
     *
     * @return
     */
    List<MoManagerType> getManagerTypeList();
}