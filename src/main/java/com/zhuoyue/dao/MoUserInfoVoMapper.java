package com.zhuoyue.dao;

import com.zhuoyue.vo.MoUserInfoVo;

public interface MoUserInfoVoMapper {
    int insert(MoUserInfoVo record);

    int insertSelective(MoUserInfoVo record);
    
    MoUserInfoVo selectByUserPhone(MoUserInfoVo record);
    
    
    MoUserInfoVo selectByUserById(String id );
}