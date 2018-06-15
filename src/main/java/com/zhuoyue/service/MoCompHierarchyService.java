package com.zhuoyue.service;

import com.zhuoyue.bean.MoCompHierarchy;
import com.zhuoyue.vo.ModelTree;

import java.util.List;

/**
 * @author 14258
 */
public interface MoCompHierarchyService {


    /**
     * 通过模型主键（modeId）获得（MoCompHierarchy）列表
     *
     * @param modeId
     * @return
     */
    List<MoCompHierarchy> getMoCompHierarchyListByModeId(String modeId);

    /**
     * 通过模型主键（modeId）获得（专业-楼层）列表
     *
     * @param modeId
     * @return
     */
    ModelTree getSpecialtyTreeByModeId(String modeId);


    /**
     * 通过模型主键（modeId）获得（楼层-专业）列表
     *
     * @param modeId
     * @return
     */
    ModelTree getFloorsTreeByModeId(String modeId);

    /**
     * 通过模型 （modeId）获得（楼层-专业）列表 IOS 测试
     *
     * @param modeId
     * @return
     */
    ModelTree getFloorsAndSpecialsByModeId(String modeId);
}