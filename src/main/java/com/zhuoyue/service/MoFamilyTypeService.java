package com.zhuoyue.service;

import com.zhuoyue.bean.MoCompElement;
import com.zhuoyue.bean.MoFamilyType;
import com.zhuoyue.vo.Family;
import com.zhuoyue.vo.ObjVo;

import java.util.List;

/**
 * @author 14258
 */
public interface MoFamilyTypeService {

    /**
     * @Description: 获得族类型列表
     * @author 14258
     * @Author:xuyangyang
     * @Date: 2017/11/27
     */
    List<MoFamilyType> getFamilyTypeListByCategoryId(String categoryId, String modeId);


    /**
     * 获得族和族类型集合树
     *
     * @param categoryId
     * @param modeId
     * @return
     */
    List<Family> getFamilyListByCategoryId(String categoryId, String modeId);


    /**
     * 通过类型id获取objvo列表
     *
     * @param categoryId
     * @param modeId
     * @return
     */
    List<ObjVo> getObjVoList(String categoryId, String modeId,String flag);


    /**
     * 获取obj下所有元素
     *
     * @param familyTypeId
     * @return
     */
    List<MoCompElement> getElementListByFamilyTypeId(String familyTypeId);

    MoCompElement getElementByIdAndName(String familyTypeId, String modeId, String elementName);

    /**
     * 通过主键id获取familyType文件
     *
     * @param familyTypeId
     * @param modeId
     * @return
     */
    MoFamilyType getFamilyType(String familyTypeId, String modeId);

    void saveMoFamilyTypes(List<MoFamilyType> familyTypes);


    /**
     * 获得所有文件ios或者安卓
     *
     * @param categoryId
     * @param modeId
     * @param flag
     * @return
     */
    List<MoFamilyType> getFamilyTypeListByCategoryIdOrFlag(String categoryId, String modeId, String flag);
}
