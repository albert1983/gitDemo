package com.zhuoyue.common.util;


import java.io.File;
import java.io.IOException;

/**
 * 调用exe（减面） 程序工具类
 *
 * @author 14258
 */
public class ExecuteExeUtil {


    /**
     * 在window上执行程序
     *
     * @param name            文件名字
     * @param uploadTempDir   文件原目录
     * @param generateTempDir 文件生成目录
     */
    public static void exeWindows(String name, String uploadTempDir, String generateTempDir) {

        File file2 = new File(generateTempDir);
        if (!file2.exists()) {
            file2.mkdirs();
        }

        String path = ExecuteExeUtil.class.getResource("/") + "lib/";
        path = path.substring(6);

        String[] cmd = new String[5];
        cmd[0] = "cmd "; //命令行
        cmd[1] = "/c "; //运行后关闭，
        cmd[2] = "start "; //启动另一个窗口来运行指定的程序或命令(cmd 命令集里的)
        cmd[3] = path; //要运行的.exe程序的目录
        //System.out.println(cmd[3]);

        cmd[4] = "mle.exe -t 50% -i  " +
                uploadTempDir + "/" + name + " -o " + generateTempDir + "/" + name + " -f 5 ";//exe程序及其需要的参数
        //System.out.println(cmd[4]);
        try {
            Process p = Runtime.getRuntime().exec((cmd[0] + cmd[1] + cmd[4]), null, new File(cmd[3]));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        String path = ExecuteExeUtil.class.getResource("/") + "lib/";
        path = path.substring(6);
        System.out.println(path);

    }


}
