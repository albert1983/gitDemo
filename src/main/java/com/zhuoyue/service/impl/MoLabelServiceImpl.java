package com.zhuoyue.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhuoyue.bean.MoLabel;
import com.zhuoyue.common.log.annotation.ServiceLog;
import com.zhuoyue.dao.MoLabelMapper;
import com.zhuoyue.service.MoLabelService;
import com.zhuoyue.vo.LabelVo;

/**
 * @author lizw
 * @date 2018/03/30 19:34
 * @desc 模型标注 服务层
 */
@Service
@Transactional 
public class MoLabelServiceImpl implements MoLabelService {

    @Autowired
    private MoLabelMapper moLableDao ; 

    
	@Override
	@ServiceLog(description = "insert into modLabel info")
	public void saveMoLabel(MoLabel record) {
		moLableDao.insertSelective(record);
	}


	@Override
	public List<LabelVo> getLabelList(LabelVo labelVo) {
		return moLableDao.getLabelList(labelVo);
	}


	@Override
	public MoLabel getMolableContent(LabelVo vo) {
		return moLableDao.getMolableContent(vo);
	}


	@Override
	public Map<String, String> getMolableImage(LabelVo vo) {
		return moLableDao.getMolableImage(vo);
	}


	@Override
	public Map<String, String> getMolableAudio(LabelVo vo) {
		return moLableDao.getMolableAudio(vo);
	}


	@Override
	public void deleteMoLable(LabelVo vo) {

		moLableDao.deleteMoLable(vo);
	}

}
