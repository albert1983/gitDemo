package com.zhuoyue.service;

import java.util.List;

import com.zhuoyue.bean.CadDesignPaper;
import com.zhuoyue.bean.CadDesignType;

public interface MoCadViewService {

    int insert(CadDesignPaper record);

    int insertSelective(CadDesignPaper record);

	List<CadDesignPaper> getCadViewListByProCode(String projectId , String orderState);

	CadDesignPaper selectByPrimaryKey(String id);
	
	
	List<CadDesignPaper> getCadViewListByProCodeAndType(String projectCode , String orderState,String type);
	
	
	//CAD type List 
	List<CadDesignType> getCadTypeList(); 
	 

	List<CadDesignPaper>  searchCadByName(CadDesignPaper record);
	
	
	
}
