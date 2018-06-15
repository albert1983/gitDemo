package com.zhuoyue.dao;

import com.zhuoyue.bean.MoCompElement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MoCompElementMapper {

    int insert(MoCompElement record);

    int insertSelective(MoCompElement record);

    /**
     * 获取obj下所有元素
     *
     * @param familyTypeId
     * @return
     */
    List<MoCompElement> getElementListByFamilyTypeId(@Param("familyTypeId") String familyTypeId);

    MoCompElement getElementByIdAndName(@Param("familyTypeId") String familyTypeId, @Param("modeId") String modeId, @Param("elementName") String elementName);
}