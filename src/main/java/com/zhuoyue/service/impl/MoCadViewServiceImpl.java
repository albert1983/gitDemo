package com.zhuoyue.service.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhuoyue.bean.CadDesignPaper;
import com.zhuoyue.bean.CadDesignType;
import com.zhuoyue.dao.CadDesignPaperMapper;
import com.zhuoyue.dao.CadDesignTypeMapper;
import com.zhuoyue.service.MoCadViewService;
 

 
@Service
@Transactional(readOnly = true)
public class MoCadViewServiceImpl implements MoCadViewService {

    @Autowired
    private CadDesignPaperMapper cadDesignPapaerDao;

    @Autowired
    private CadDesignTypeMapper cadDesignTypeDao;
    
	@Override
	public int insert(CadDesignPaper record) {
		 
		return cadDesignPapaerDao.insert(record);
	}

	@Override
	public int insertSelective(CadDesignPaper record) {
		return cadDesignPapaerDao.insertSelective(record);
	}

	@Override
	public List<CadDesignPaper> getCadViewListByProCode(String projectId , String orderState ) {
		
		HashMap<String,String> map = new HashMap<>();
		map.put("projectId", projectId);
		map.put("orderState", orderState);
		return cadDesignPapaerDao.getCadViewListByProCode(map);
	}

	@Override
	public CadDesignPaper selectByPrimaryKey(String id) {
		
		return cadDesignPapaerDao.selectByPrimaryKey(id);
	}

	
	@Override
	public List<CadDesignPaper> getCadViewListByProCodeAndType(String projectId, String orderState, String type) {
		HashMap<String,String> map = new HashMap<>();
		map.put("projectId", projectId);
		map.put("orderState", orderState);
		map.put("type", type);
		return cadDesignPapaerDao.getCadViewListByProCodeAndType(map);
	}
    	
	
	@Override
	public List<CadDesignType> getCadTypeList() {
		 
		return cadDesignTypeDao.getCadTypeList();
	}

	@Override
	public List<CadDesignPaper> searchCadByName(CadDesignPaper record) {
		 
		return  cadDesignPapaerDao.searchCadByName(record);
	}


 

}
