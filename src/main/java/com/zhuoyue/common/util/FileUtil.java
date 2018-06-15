package com.zhuoyue.common.util;

import com.zhuoyue.bean.MoFamilyType;
import com.zhuoyue.common.exception.ServiceException;
import com.zhuoyue.common.web.config.GlobalValue;
import com.zhuoyue.vo.ObjVo;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.csource.common.MyException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 文件目录操作工具类
 *
 * @author 14258
 */
public class FileUtil {


	 private static Logger logger = Logger.getLogger(FileUtil.class);
	
    /**
     * 打包下载zip文件
     *
     * @param categoryName
     * @param response
     * @param objVoList
     * @return
     * @throws IOException
     */
    public static String packageDownloadFile(String categoryName, HttpServletResponse response, List<ObjVo> objVoList) throws IOException {

        //存储zip文件路径
        String path = GlobalValue.fileSavePath;
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new ServiceException("创建目录失败");
            }
        }
        String filePath = path + categoryName + UUIDUtil.buildUUID() + ".zip";

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(filePath));
        //设置压缩文件内的字符编码，不然会变成乱码
        zos.setEncoding("UTF-8");
        byte[] buffer = new byte[1024];

        Set<String> familyNameSet = new HashSet<>();
        for (ObjVo objVo : objVoList) {
            familyNameSet.add(objVo.getFamily());
        }
        for (String familyName : familyNameSet) {
            //写入obj
            for (ObjVo objVo : objVoList) {
                if (familyName.equals(objVo.getFamily())) {
                    InputStream inputStream = FastDfsClientUtil.downloadFile(objVo.getObjFilePath());
                    if (null != inputStream) {
                        String extName = FastDfsClientUtil.getFileExt(objVo.getObjFilePath());
                        zos.putNextEntry(new ZipEntry(familyName + "/" + objVo.getObjName() + "/" + objVo.getObjName() + "." + extName));
                        int len;
                        while ((len = inputStream.read(buffer)) > 0) {
                            zos.write(buffer, 0, len);
                        }
                        zos.closeEntry();
                        inputStream.close();
                    }

                }
            }

            //写入材质(只有一个材质文件)
            for (ObjVo objVo : objVoList) {
                if (familyName.equals(objVo.getFamily())) {
                    InputStream inputStream = FastDfsClientUtil.downloadFile(objVo.getJpgFilePath());
                    if (null != inputStream) {
                        String extName = FastDfsClientUtil.getFileExt(objVo.getJpgFilePath());
                        zos.putNextEntry(new ZipEntry(familyName + "/" + objVo.getObjName() + "/" + objVo.getObjName() + "." + extName));
                        int len;
                        while ((len = inputStream.read(buffer)) > 0) {
                            zos.write(buffer, 0, len);
                        }
                        zos.closeEntry();
                        inputStream.close();
                    }
                }
            }

            //写入txt
            for (ObjVo objVo : objVoList) {
                if (familyName.equals(objVo.getFamily())) {
                    String extName = "txt";
                    InputStream inputStream = new ByteArrayInputStream(objVo.getRbg().getBytes());
                    zos.putNextEntry(new ZipEntry(familyName + "/" + objVo.getObjName() + "/" + objVo.getObjName() + "." + extName));
                    int len;
                    while ((len = inputStream.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                    zos.closeEntry();
                    inputStream.close();
                }
            }
        }
        zos.flush();
        zos.close();


        //浏览器下载文件
        //response.setHeader("content-type", "application/octet-stream");
        //response.setContentType("application/octet-stream");
        String formatFileName = FastDfsClientUtil.encodingFileName(categoryName);
        response.setHeader("Content-Disposition", "attachment; filename=" + formatFileName + ".zip");
        OutputStream outStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream(new File(filePath));
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outStream.write(buffer, 0, length);
        }
        // 这里主要关闭。
        outStream.flush();
        outStream.close();
        response.flushBuffer();
        inputStream.close();
        //将文件删除
        new File(filePath).delete();

        //浏览器下载文件
        return filePath;


    }

    public static String packageDownloadFile(String modeId, String categoryId, HttpServletResponse response, List<ObjVo> objVoList) throws IOException {


        if (objVoList.isEmpty()) {
            throw new ServiceException("无此模型文件");
        }


        //存储zip文件路径
        String path = GlobalValue.fileSavePath;
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new ServiceException("创建目录失败");
            }
        }


        String filePath = path + modeId + ".zip";

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(filePath));
        //设置压缩文件内的字符编码，不然会变成乱码
        zos.setEncoding("UTF-8");
        byte[] buffer = new byte[1024];


        //写入obj
        for (ObjVo objVo : objVoList) {

            InputStream inputStream = FastDfsClientUtil.downloadFile(objVo.getObjFilePath());
            if (null != inputStream) {
                String extName = FastDfsClientUtil.getFileExt(objVo.getObjFilePath());
                zos.putNextEntry(new ZipEntry(categoryId + File.separator + objVo.getObjName() + "." + extName));
                int len;
                while ((len = inputStream.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                inputStream.close();
            }


        }

        //写入材质(只有一个材质文件)
        for (ObjVo objVo : objVoList) {
            if (null != objVo.getJpgFilePath()) {

                InputStream inputStream = FastDfsClientUtil.downloadFile(objVo.getJpgFilePath());
                if (null != inputStream) {
                    String extName = FastDfsClientUtil.getFileExt(objVo.getJpgFilePath());
                    zos.putNextEntry(new ZipEntry(categoryId + File.separator + objVo.getId() + "." + extName));
                    int len;
                    while ((len = inputStream.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                    zos.closeEntry();
                    inputStream.close();
                }
            }

        }

        //写入txt
        for (ObjVo objVo : objVoList) {
            if (null != objVo.getRbg()) {
                String extName = "txt";
                InputStream inputStream = new ByteArrayInputStream(objVo.getRbg().getBytes());
                zos.putNextEntry(new ZipEntry(categoryId + File.separator + objVo.getId() + "." + extName));
                int len;
                while ((len = inputStream.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                inputStream.close();
            }
        }

        zos.flush();
        zos.close();


        //浏览器下载文件
        //response.setHeader("content-type", "application/octet-stream");
        //response.setContentType("application/octet-stream");
        // String formatFileName = FastDfsClientUtil.encodingFileName(categoryName);
        response.setHeader("Content-Disposition", "attachment; filename=" + modeId + ".zip");
        OutputStream outStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream(new File(filePath));
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outStream.write(buffer, 0, length);
        }
        // 这里主要关闭。
        outStream.flush();
        outStream.close();
        response.flushBuffer();
        inputStream.close();
        //将文件删除
        //new File(filePath).delete();

        //浏览器下载文件
        return filePath;

    }

    /**
     * 下载obj文件
     *
     * @param filePath
     * @param fileName
     * @param response
     * @return
     * @throws IOException
     */
    public static String downLoadObj(String filePath, String fileName, HttpServletResponse response) throws IOException {

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        //设置文件名字
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + UUIDUtil.buildUUID() + ".obj");

        InputStream inputStream = FastDfsClientUtil.downloadFile(filePath);
        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        // 这里主要关闭。
        outputStream.flush();
        outputStream.close();
        response.flushBuffer();
        inputStream.close();
        return filePath;
    }

    /**
     * 下载图片文件
     *
     * @param filePath
     * @param fileName
     * @param response
     * @return
     * @throws IOException
     */
    public static String downLoadImageOrAudio(String filePath, String fileName, HttpServletResponse response) throws IOException {

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        //设置文件名字
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        OutputStream outputStream = response.getOutputStream();
        
        logger.info("filePath  :::::  "+filePath);
        
        byte[]  downFileBytes =  FastDFS.downloadFile(filePath);
        
        if(null != downFileBytes ){
        	 logger.info("downFileBytes  :::::"+downFileBytes.length);
        	outputStream.write(downFileBytes );
        }else{
        	System.out.println(" 获取参downFileBytes数的bytes  为0");
        }
          
        // 这里主要关闭。
        outputStream.flush();
        outputStream.close();
        response.flushBuffer(); 
        return filePath;
    }
    /**
     * 上传文件
     *
     * @param multiparFile
     * @return
     */
    public static String uploadfile(MultipartFile multiparFile) {
        String fileId = null;
        try {
            fileId = FastDfsClientUtil.upload(multiparFile);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        return fileId;

    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public static String uploadfile(File file) {
        String fileName = file.getName();
        String fileId = null;
        fileId = FastDfsClientUtil.uploadFile(file, fileName);
        return fileId;

    }


    /**
     * 保存上传文件到临时目录
     *
     * @param file
     * @param uploadTempDir
     * @return
     */
    public static boolean saveFile(MultipartFile file, String uploadTempDir) {

        File file1 = new File(uploadTempDir);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        if (!file.isEmpty()) {
            try {
                file.transferTo(new File(uploadTempDir + File.separator + file.getOriginalFilename()));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 删除空目录
     *
     * @param dir 将要删除的目录路径
     */
    public static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();

            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }


    /**
     * 下载u3d生成文件
     *
     * @param modeId
     * @param categoryId
     * @param response
     * @param moFamilyTypeList
     * @return
     * @throws IOException
     */
    public static String packageDownloadU3dFile(String modeId, String categoryId, HttpServletResponse response, List<MoFamilyType> moFamilyTypeList) throws IOException {
        if (moFamilyTypeList.isEmpty()) {
            throw new ServiceException("无此模型文件");
        }

        //存储zip文件路径
        String path = GlobalValue.fileSavePath;
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new ServiceException("创建目录失败");
            }
        }
        String filePath = path + modeId + ".zip";
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //设置压缩文件内的字符编码，不然会变成乱码
        zos.setEncoding("UTF-8");
        byte[] buffer = new byte[1024];


        //写入obj
        for (MoFamilyType moFamilyType : moFamilyTypeList) {

            InputStream inputStream = FastDfsClientUtil.downloadFile(moFamilyType.getFilePath());
            if (null != inputStream) {
                String extName = FastDfsClientUtil.getFileExt(moFamilyType.getFilePath());
                try {
                    zos.putNextEntry(new ZipEntry(categoryId + File.separator + moFamilyType.getName() + "." + extName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int len;
                while ((len = inputStream.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                inputStream.close();
            }


        }


        zos.flush();
        zos.close();


        InputStream inputStream = new FileInputStream(new File(filePath));
        response.setHeader("Content-Disposition", "attachment; filename=" + modeId + ".zip");
        OutputStream outStream = response.getOutputStream();
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outStream.write(buffer, 0, length);
        }
        // 这里主要关闭。
        outStream.flush();
        outStream.close();
        response.flushBuffer();
        inputStream.close();
        //将文件删除
        //new File(filePath).delete();

        //浏览器下载文件
        return filePath;
    }

    
    public static String packageDownloadAssetBoundleFile(String modeId, String categoryId, HttpServletResponse response, List<ObjVo> objVoList) throws IOException {


        if (objVoList.isEmpty()) {
            throw new ServiceException("无此模型文件");
        }
        //存储zip文件路径
        String path = GlobalValue.fileSavePath;
        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new ServiceException("创建目录失败");
            }
        }

        String filePath = path + modeId + ".zip";
        byte[] buffer = new byte[1024];

        //写入obj
        for (ObjVo objVo : objVoList) {

            InputStream inputStream = FastDfsClientUtil.downloadFile(objVo.getObjFilePath());
            response.setHeader("Content-Disposition", "attachment; filename=" + categoryId + ".zip");
            OutputStream outStream = response.getOutputStream();
             
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            // 这里主要关闭。
            outStream.flush();
            outStream.close();
            response.flushBuffer();
            inputStream.close();
            break ;
        }
 
  
        //浏览器下载文件
        return filePath;

    }    
    
    
    
}
