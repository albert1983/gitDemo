package com.zhuoyue.service;

import java.util.HashMap;
import java.util.List;

import com.zhuoyue.bean.ProDocFile;

public interface ProDocFileService {
    int insert(ProDocFile record);

    int insertSelective(ProDocFile record);
    
	List<ProDocFile> getProDocListByProCode(HashMap<String,String> map);

	ProDocFile selectByPrimaryKey(String id);
	
	List<ProDocFile> searchDocFileByName(ProDocFile record);
    
}