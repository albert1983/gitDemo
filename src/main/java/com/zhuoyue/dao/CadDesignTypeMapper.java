package com.zhuoyue.dao;

import java.util.List;

import com.zhuoyue.bean.CadDesignType;

public interface CadDesignTypeMapper {
	
    int insert(CadDesignType record);

    int insertSelective(CadDesignType record);
    
    List<CadDesignType> getCadTypeList(); 
    
    
    
}