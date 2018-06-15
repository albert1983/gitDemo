package com.zhuoyue.dao;

import java.util.List;
import java.util.Map;

import com.zhuoyue.bean.MoLabel;
import com.zhuoyue.vo.LabelVo;

public interface MoLabelMapper {
	int insert(MoLabel record);

	int insertSelective(MoLabel record);

	List<LabelVo> getLabelList(LabelVo labelVo);

	MoLabel getMolableContent(LabelVo vo);

	Map<String, String> getMolableImage(LabelVo vo);

	Map<String, String> getMolableAudio(LabelVo vo);
	
	void deleteMoLable(LabelVo vo);
	
}