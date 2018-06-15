package com.zhuoyue.service.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhuoyue.bean.Hello;
import com.zhuoyue.bean.ProDocFile;
import com.zhuoyue.bean.TestTable;
import com.zhuoyue.common.db.DataSourceNames;
import com.zhuoyue.common.db.annotation.DataSource;
import com.zhuoyue.common.log.annotation.ServiceLog;
import com.zhuoyue.dao.HelloMapper;
import com.zhuoyue.dao.ProDocFileMapper;
import com.zhuoyue.dao.TestTableMapper;
import com.zhuoyue.service.HelloService;
import com.zhuoyue.service.ProDocFileService;
 

/**
 * @author lizw
 * @date 2017/10/26 19:34
 * @desc input the desc
 */
@Service
@Transactional(readOnly = true)
public class ProDocFileServiceImpl implements ProDocFileService {

    @Autowired
    private ProDocFileMapper proDocFileDao;

	@Override
	public int insert(ProDocFile record) {
		 
		return proDocFileDao.insert(record);
	}

	@Override
	public int insertSelective(ProDocFile record) {
		 
		return proDocFileDao.insertSelective(record);
	}

	@Override
	public List<ProDocFile> getProDocListByProCode(HashMap<String, String> map) {
		 
		return proDocFileDao.getProDocListByProCode(map);
	}

	@Override
	public ProDocFile selectByPrimaryKey(String id) {
		 
		return proDocFileDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ProDocFile> searchDocFileByName(ProDocFile record) {
	 
		return proDocFileDao.searchDocFileByName(record);
	}
     
	
  
}
