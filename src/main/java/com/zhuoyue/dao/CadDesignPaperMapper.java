package com.zhuoyue.dao;

import java.util.HashMap;
import java.util.List;

import com.zhuoyue.bean.CadDesignPaper;

public interface CadDesignPaperMapper {
    int insert(CadDesignPaper record);

    int insertSelective(CadDesignPaper record);
    
 	List<CadDesignPaper> getCadViewListByProCode(HashMap<String,String> map);

	CadDesignPaper selectByPrimaryKey(String id);
    
	List<CadDesignPaper> getCadViewListByProCodeAndType(HashMap<String,String> map);
    
	List<CadDesignPaper>  searchCadByName(CadDesignPaper record);
	
}