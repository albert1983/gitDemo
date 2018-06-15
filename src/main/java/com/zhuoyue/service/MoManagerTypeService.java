package com.zhuoyue.service;

import com.zhuoyue.bean.MoManagerType;
import com.zhuoyue.bean.MoStempInfo;

import java.util.List;

/**
 * @author 14258
 */
public interface MoManagerTypeService {


    /**
     * 获取管理类型列表成功
     *
     * @return
     */
    List<MoManagerType> getManagerTypeList();


    /**
     * 获取阶段列表通过类型
     *
     * @param type
     * @return
     */
    List<MoStempInfo> getStempInfoListByType(String type);
}
