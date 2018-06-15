package com.zhuoyue.dao;

import com.zhuoyue.bean.MoCompHierarchy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 14258
 */
public interface MoCompHierarchyMapper {


    /**
     * 通过模型主键（modeId）获得（MoCompHierarchy）列表
     *
     * @param modeId
     * @return
     */
    List<MoCompHierarchy> getMoCompHierarchyListByModeId(@Param("modeId") String modeId);
    
    
    /**
     * 通过模型主键（modeId）获得（MoCompHierarchy）列表 IOS
     *
     * @param modeId
     * @return
     */
    List<MoCompHierarchy> getMoCompHierarchyListByModeIdIOS(@Param("modeId") String modeId);
    
    
    

}