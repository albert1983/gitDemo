package com.zhuoyue.dao;

import com.zhuoyue.bean.MoCommentScreanScreen;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MoCommentScreanScreenMapper {

    /**
     * 保存屏幕截图
     */
    void saveScreanScreen(MoCommentScreanScreen commentScreanScreen);
}