package com.zhuoyue.dao;

import com.zhuoyue.bean.MoCompMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoCompMaterialMapper {
    int insert(MoCompMaterial record);

    int insertSelective(MoCompMaterial record);

    /**
     * @Description: 获取每个obj 下的RPG和材质
     * @author 14258
     * @Author:xuyangyang
     * @Date: 2017/11/24
     */
    List<MoCompMaterial> getMoComMaterialPartList(@Param("objId") String objId);

    List<MoCompMaterial> getMoComMaterialById(@Param("familyTypeId") String familyTypeId);
}