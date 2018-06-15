package com.zhuoyue.service;

import com.zhuoyue.vo.MoUserInfoVo;

/**
 * @author lizw
 */
public interface MoUserInfoService {

	 MoUserInfoVo selectByUserPhone(MoUserInfoVo record);
	 
	 
	 MoUserInfoVo selectByUserById(String id );
	 
}
