package com.zhuoyue.common.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;

public class FastDfsClientUtil {


    private static StorageClient1 storageClient1 = null;

    private static Logger logger = Logger.getLogger(FastDfsClientUtil.class);


    /**
     * 只加载一次.
     */
    static {
        try {
            logger.info("=== CONF_FILENAME: init start ");
            ClientGlobal.initByProperties("properties/fastdfs-client.properties");
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            TrackerServer trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                logger.error("getConnection return null");
            }
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            if (storageServer == null) {
                logger.error("getStoreStorage return null");
            }
            storageClient1 = new StorageClient1(trackerServer, storageServer);
        } catch (Exception e) {
            logger.error(e);
        }
    }


    public static String upload(MultipartFile file) throws IOException, MyException {
        String fileId = storageClient1.upload_file1(file.getBytes(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return fileId;
    }

    /**
     * @param file     文件
     * @param fileName 文件名
     * @return 返回Null则为失败
     */
    public static String uploadFile(File file, String fileName) {
        FileInputStream fis = null;
        try {
            NameValuePair[] meta_list = null; // new NameValuePair[0];
            fis = new FileInputStream(file);
            byte[] file_buff = null;
            if (fis != null) {
                int len = fis.available();
                file_buff = new byte[len];
                fis.read(file_buff);
            }

            String fileid = storageClient1.upload_file1(file_buff, getFileExt(fileName), meta_list);

            return fileid;
        } catch (Exception ex) {
            logger.error(ex);
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
    }

    /**
     * 上传文件（Byte）
     *
     * @return
     * @author 作者: mazhao
     * @date 创建时间：2017年7月17日下午2:00:45
     * @version 1.0
     * @parameter
     */
    public static String uploadFileBytes(byte[] fileByte, String fileName) {
        try {
            String fileId = storageClient1.upload_file1(fileByte, getFileExt(fileName), null);
            return fileId;

        } catch (Exception ex) {
            logger.error(ex);
            return null;
        }

    }

    /**
     * 根据组名和远程文件名来删除一个文件
     *
     * @param groupName 例如 "group1" 如果不指定该值，默认为group1
     * @param fileName  例如"M00/00/00/wKgxgk5HbLvfP86RAAAAChd9X1Y736.jpg"
     * @return 0为成功，非0为失败，具体为错误代码
     */
    public static int deleteFile(String groupName, String fileName) {
        try {
            int result = storageClient1.delete_file(groupName == null ? "group1" : groupName, fileName);
            return result;
        } catch (Exception ex) {
            logger.error(ex);
            return 0;
        }
    }

    /**
     * 根据fileId来删除一个文件（我们现在用的就是这样的方式，上传文件时直接将fileId保存在了数据库中）
     *
     * @param fileId file_id源码中的解释file_id the file id(including group name and filename);例如 group1/M00/00/00/ooYBAFM6MpmAHM91AAAEgdpiRC0012.xml
     * @return 0为成功，非0为失败，具体为错误代码
     */
    public static int deleteFile(String fileId) {
        try {
            int result = storageClient1.delete_file1(fileId);
            return result;
        } catch (Exception ex) {
            logger.error(ex);
            return 0;
        }
    }

    /**
     * 修改一个已经存在的文件
     *
     * @param oldFileId 原来旧文件的fileId, file_id源码中的解释file_id the file id(including group name and filename);例如 group1/M00/00/00/ooYBAFM6MpmAHM91AAAEgdpiRC0012.xml
     * @param file      新文件
     * @param filePath  新文件路径
     * @return 返回空则为失败
     */
    public static String modifyFile(String oldFileId, File file, String filePath) {
        String fileid = null;
        try {
            // 先上传
            fileid = uploadFile(file, filePath);
            if (fileid == null) {
                return null;
            }
            // 再删除
            int delResult = deleteFile(oldFileId);
            if (delResult != 0) {
                return null;
            }
        } catch (Exception ex) {
            logger.error(ex);
            return null;
        }
        return fileid;
    }

    /**
     * 文件下载
     *
     * @param fileId
     * @return 返回一个流
     */
    public static InputStream downloadFile(String fileId) {
        try {
            byte[] bytes = storageClient1.download_file1(fileId);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            return inputStream;
        } catch (Exception ex) {
            logger.error(ex);
            return null;
        }
    }

    public static byte[] downloadFileByte(String fileId) {
        try {
            byte[] bytes = storageClient1.download_file1(fileId);
            return bytes;
        } catch (Exception ex) {
            logger.error(ex);
            return null;
        }
    }

    /**
     * 获取文件后缀名（不带点）.
     *
     * @return 如："jpg" or "".
     */
    public static String getFileExt(String fileName) {
        if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
            return "";
        } else {
            return fileName.substring(fileName.lastIndexOf(".") + 1); // 不带最后的点
        }
    }

    public static void main(String[] args) {

        String filePath = "E:/环境科学楼含机电.mpp";
        File file = new File(filePath);
        String fileId = uploadFile(file, filePath);
        System.out.println("Upload local file " + filePath + " ok, fileid=" + fileId);
        //http://10.10.80.27:8888/group1/M00/00/00/CgpQG1lizwOAcB1tAAWYAMquS-c759.mpp

        deleteFile(fileId);

    }


    public static String encodingFileName(String fileName) {
        String returnFileName = "";
        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if (returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
                returnFileName = StringUtils.replace(returnFileName, " ", "%20");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnFileName;
    }

}
