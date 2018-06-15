package com.zhuoyue.service;


public interface MoCommentScreanScreenService {


    /**
     * 保存屏幕截图
     *
     * @param name
     * @param typeFileId
     * @param des
     * @param filePath
     */
    void saveScreanScreen(String name, String typeFileId, String des, String cameraSize, String modelRotation, String filePath);
}
