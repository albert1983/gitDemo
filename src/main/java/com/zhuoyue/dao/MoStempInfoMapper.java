package com.zhuoyue.dao;

import com.zhuoyue.bean.MoStempInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoStempInfoMapper {
    int insert(MoStempInfo record);

    int insertSelective(MoStempInfo record);


    /**
     * 获取阶段列表通过类型
     *
     * @param type
     * @return
     */
    List<MoStempInfo> getStempInfoListByType(@Param("type") String type);
}