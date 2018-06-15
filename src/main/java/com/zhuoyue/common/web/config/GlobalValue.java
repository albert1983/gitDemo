package com.zhuoyue.common.web.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 14258
 */
@Component
public class GlobalValue {

    @Value("${file_save_path}")
    public static String fileSavePath;


    @Value("${exe_dir}")
    public static String exeDir;


    @Value("${file_save_path}")
    public void setfileSavePath(String file_save_path) {
        fileSavePath = file_save_path;
    }

    @Value("${exe_dir}")
    public void setExeDir(String exe_dir) {
        exeDir = exe_dir;
    }


}
