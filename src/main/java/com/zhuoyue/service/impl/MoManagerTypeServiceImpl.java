package com.zhuoyue.service.impl;

import com.zhuoyue.bean.MoManagerType;
import com.zhuoyue.bean.MoStempInfo;
import com.zhuoyue.dao.MoManagerTypeMapper;
import com.zhuoyue.dao.MoStempInfoMapper;
import com.zhuoyue.service.MoManagerTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 14258
 */
@Service
public class MoManagerTypeServiceImpl implements MoManagerTypeService {


    @Resource
    private MoManagerTypeMapper managerTypeMapper;
    @Resource
    private MoStempInfoMapper stempInfoMapper;


    /**
     * 获取管理类型列表成功
     *
     * @return
     */
    @Override
    public List<MoManagerType> getManagerTypeList() {
        List<MoManagerType> moManagerTypeList = managerTypeMapper.getManagerTypeList();
        return moManagerTypeList;
    }


    /**
     * 获取阶段列表通过类型
     *
     * @param type
     * @return
     */
    @Override
    public List<MoStempInfo> getStempInfoListByType(String type) {
        List<MoStempInfo> stempInfoList = stempInfoMapper.getStempInfoListByType(type);
        return stempInfoList;
    }
}
