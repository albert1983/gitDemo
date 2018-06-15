package com.zhuoyue.dao;

import com.zhuoyue.bean.MoFamilyType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 14258
 */
public interface MoFamilyTypeMapper {

    int insert(MoFamilyType record);

    int insertSelective(MoFamilyType record);

    /**
     * @Description: 获得族类型列表
     * @author 14258
     * @Author:xuyangyang
     * @Date: 2017/11/27
     */
    List<MoFamilyType> getFamilyTypeListByCategoryId(@Param("categoryId") String categoryId, @Param("modeId") String modeId);

    MoFamilyType getFamilyTypeId(@Param("familyTypeId") String familyTypeId);


    /**
     * 通过主键id获取familyType文件
     *
     * @param familyTypeId
     * @param modeId
     * @return
     */
    MoFamilyType getFamilyType(@Param("familyTypeId") String familyTypeId, @Param("modeId") String modeId);

    void saveMoFamilyTypes(List<MoFamilyType> familyTypes);

    /**
     * 获得所有文件ios或者安卓
     *
     * @param categoryId
     * @param modeId
     * @param flag
     * @return
     */
    List<MoFamilyType> getFamilyTypeListByCategoryIdOrFlag(@Param("categoryId") String categoryId, @Param("modeId") String modeId, @Param("flag") String flag);
}