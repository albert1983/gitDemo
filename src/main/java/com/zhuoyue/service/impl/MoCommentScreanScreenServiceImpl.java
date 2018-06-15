package com.zhuoyue.service.impl;

import com.zhuoyue.bean.MoCommentScreanScreen;
import com.zhuoyue.common.util.UUIDUtil;
import com.zhuoyue.dao.MoCommentScreanScreenMapper;
import com.zhuoyue.service.MoCommentScreanScreenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MoCommentScreanScreenServiceImpl implements MoCommentScreanScreenService {


    @Resource
    MoCommentScreanScreenMapper commentScreanScreenMapper;

    /**
     * 保存屏幕截图
     *
     * @param name
     * @param typeFileId
     * @param des
     * @param filePath
     */
    @Override
    public void saveScreanScreen(String name, String typeFileId, String des, String cameraSize, String modelRotation, String filePath) {

        MoCommentScreanScreen commentScreanScreen = new MoCommentScreanScreen();
        commentScreanScreen.setId(UUIDUtil.buildUUID());
        commentScreanScreen.setName(name);
        commentScreanScreen.setDes(des);
        commentScreanScreen.setTypeFileId(typeFileId);
        commentScreanScreen.setFilePath(filePath);
        commentScreanScreen.setModelRotation(modelRotation);
        commentScreanScreen.setCameraSize(cameraSize);
        commentScreanScreenMapper.saveScreanScreen(commentScreanScreen);

    }
}
