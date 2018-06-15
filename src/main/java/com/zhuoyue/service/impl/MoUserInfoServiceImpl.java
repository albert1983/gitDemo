package com.zhuoyue.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhuoyue.dao.MoUserInfoVoMapper;
import com.zhuoyue.service.MoUserInfoService;
import com.zhuoyue.vo.MoUserInfoVo;
 
@Service
@Transactional(readOnly = true)
public class MoUserInfoServiceImpl implements MoUserInfoService {
	@Autowired
	private MoUserInfoVoMapper moUserInfoVoDao;
	@Override
	public MoUserInfoVo selectByUserPhone(MoUserInfoVo record) {
		 
		return moUserInfoVoDao.selectByUserPhone(record);
	}
	@Override
	public MoUserInfoVo selectByUserById(String id) {
		 
		return moUserInfoVoDao.selectByUserById(id);
	}

}
