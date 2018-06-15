package com.zhuoyue.service.impl;

import com.zhuoyue.bean.MoCompElement;
import com.zhuoyue.bean.MoCompMaterial;
import com.zhuoyue.bean.MoFamilyType;
import com.zhuoyue.dao.MoCompElementMapper;
import com.zhuoyue.dao.MoCompMaterialMapper;
import com.zhuoyue.dao.MoFamilyTypeMapper;
import com.zhuoyue.service.MoFamilyTypeService;
import com.zhuoyue.vo.Family;
import com.zhuoyue.vo.FamilyType;
import com.zhuoyue.vo.ObjVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 14258
 */
@Service
@Transactional
public class MoFamilyTypeServiceImpl implements MoFamilyTypeService {


    @Resource
    private MoFamilyTypeMapper moFamilyTypeMapper;
    @Resource
    private MoCompMaterialMapper moCompMaterialMapper;
    @Resource
    private MoCompElementMapper moCompElementMapper;

    /**
     * @Description: 获得族类型列表
     * @author 14258
     * @Author:xuyangyang
     * @Date: 2017/11/27
     */
    @Override
    public List<MoFamilyType> getFamilyTypeListByCategoryId(String categoryId, String modeId) {
        List<MoFamilyType> moFamilyTypeList = moFamilyTypeMapper.getFamilyTypeListByCategoryId(categoryId, modeId);
        return moFamilyTypeList;
    }

    /**
     * 获得族和族类型集合树
     *
     * @param categoryId
     * @param modeId
     * @return
     */
    @Override
    public List<Family> getFamilyListByCategoryId(String categoryId, String modeId) {

        List<MoFamilyType> moFamilyTypeList = getFamilyTypeListByCategoryId(categoryId, modeId);

        Set<String> familySet = new HashSet<>();
        for (MoFamilyType mof : moFamilyTypeList) {
            familySet.add(mof.getFamily());
        }

        List<Family> familyList = new ArrayList<>();
        for (String family : familySet) {
            Family family1 = new Family();
            family1.setFamily(family);
            List<FamilyType> familyTypeList = new ArrayList<>();
            for (MoFamilyType mft : moFamilyTypeList) {
                if (family.equals(mft.getFamily())) {
                    FamilyType familyType = new FamilyType();
                    familyType.setId(mft.getId());
                    familyType.setName(mft.getName());
                    familyTypeList.add(familyType);
                }
            }
            family1.setFamilyTypes(familyTypeList);
            familyList.add(family1);

        }

        return familyList;
    }


    /**
     * 通过类型id获取objvo列表
     *
     * @param categoryId
     * @param modeId
     * @return
     */
    @Override
    public List<ObjVo> getObjVoList(String categoryId, String modeId, String flag) {
        List<MoFamilyType> moFamilyTypeList = getFamilyTypeListByCategoryIdOrFlag(categoryId, modeId, flag);

        List<ObjVo> objVoList = new ArrayList<>();
        for (MoFamilyType mft : moFamilyTypeList) {
            ObjVo objVo = getObjVoByFamilyTypeId(mft.getId());
            objVoList.add(objVo);
        }
        return objVoList;
    }

    /**
     * 获取obj下所有元素
     *
     * @param familyTypeId
     * @return
     */
    @Override
    public List<MoCompElement> getElementListByFamilyTypeId(String familyTypeId) {
        List<MoCompElement> moCompElementList = moCompElementMapper.getElementListByFamilyTypeId(familyTypeId);
        return moCompElementList;
    }

    @Override
    public MoCompElement getElementByIdAndName(String familyTypeId, String modeId, String elementName) {
        MoCompElement moCompElement = moCompElementMapper.getElementByIdAndName(familyTypeId, modeId, elementName);
        return moCompElement;
    }

    /**
     * 通过主键id获取familyType文件
     *
     * @param familyTypeId
     * @param modeId
     * @return
     */
    @Override
    public MoFamilyType getFamilyType(String familyTypeId, String modeId) {
        MoFamilyType familyType = moFamilyTypeMapper.getFamilyType(familyTypeId, modeId);
        return familyType;
    }

    @Override
    public void saveMoFamilyTypes(List<MoFamilyType> familyTypes) {
        moFamilyTypeMapper.saveMoFamilyTypes(familyTypes);

    }

    /**
     * 获得所有文件ios或者安卓
     *
     * @param categoryId
     * @param modeId
     * @param flag
     * @return
     */
    @Override
    public List<MoFamilyType> getFamilyTypeListByCategoryIdOrFlag(String categoryId, String modeId, String flag) {
        List<MoFamilyType> moFamilyTypeList = moFamilyTypeMapper.getFamilyTypeListByCategoryIdOrFlag(categoryId, modeId, flag);
        return moFamilyTypeList;
    }

    /**
     * 通过族类型id获取每一个objvo 文件夹
     *
     * @param familyTypeId
     * @return
     */
    public ObjVo getObjVoByFamilyTypeId(String familyTypeId) {
        MoFamilyType moFamilyType = moFamilyTypeMapper.getFamilyTypeId(familyTypeId);
        ObjVo objVo = new ObjVo();
        objVo.setId(moFamilyType.getId());
        //类别
        objVo.setFamily(moFamilyType.getFamily());
        //obj 名字
        objVo.setObjName(moFamilyType.getName());
        //obj 路径
        objVo.setObjFilePath(moFamilyType.getFilePath());
        List<MoCompMaterial> materialList = moCompMaterialMapper.getMoComMaterialById(moFamilyType.getId());
        for (MoCompMaterial mom : materialList) {
            //rbg  像素 text 内容
            if (StringUtils.isNotEmpty(mom.getRbg())) {
                objVo.setRbg(mom.getRbg());
                //    jpf 材质 路径 （单个）
            } else if (StringUtils.isNotEmpty(mom.getFilePath())) {
                objVo.setJpgFilePath(mom.getFilePath());
            }
        }
        return objVo;
    }


}
