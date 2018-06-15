package com.zhuoyue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhuoyue.bean.MoCompElement;
import com.zhuoyue.bean.MoFamilyType;
import com.zhuoyue.common.dto.JsonResult;
import com.zhuoyue.common.util.ExecuteExeUtil;
import com.zhuoyue.common.util.FileUtil;
import com.zhuoyue.common.util.UUIDUtil;
import com.zhuoyue.common.web.BaseController;
import com.zhuoyue.service.MoFamilyTypeService;
import com.zhuoyue.vo.ObjVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 14258
 */
@Api(value = "API - MoFamilyTypeController", description = "obj 文件接口详情")
@RestController
@RequestMapping(value = "/mcfamilyType/")
public class MoFamilyTypeController extends BaseController {


    @Resource
    private MoFamilyTypeService moFamilyTypeService;  //获取配置文件中，打包目录
    @Value("${uploadTempDir}")
    private String uploadTempDir;
    @Value("${generateTempDir}")
    private String generateTempDir;

    /**
     * @Description: 获得族类型列表
     * @author 14258
     * @Author:xuyangyang
     * @Date: 2017/11/27
     */
    @ApiOperation(value = "获得族类型列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),
            @ApiImplicitParam(name = "categoryId", value = "族型id", required = true, dataType = "String", paramType = "query", defaultValue = "9EE7EB9BEB2B4DB7BC3D7F2F3DBB6328")
    })
    @GetMapping(value = "/getFamilyTypeListByCategoryId")
    public JsonResult getFamilyTypeListByCategoryIdAndModeId(@RequestParam String categoryId, @RequestParam String modeId) {
        List<MoFamilyType> moFamilyTypeList = moFamilyTypeService.getFamilyTypeListByCategoryId(categoryId, modeId);
        return ok("成功", moFamilyTypeList);
    }


    /**
     * 打包下载zip文件
     *
     * @param categoryId   类别id
     * @param modeId       模型id
     * @param categoryName 类别名字
     * @param response
     * @return 文件本地保存路径
     * @throws IOException
     */
    //@ApiOperation(value = "打包下载zip文件", notes = "参数乱码categoryName目前乱码，手动修改请求参数，复制url到浏览器地址栏请求")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),
    //        @ApiImplicitParam(name = "categoryId", value = "类型id", required = true, dataType = "String", paramType = "query", defaultValue = "9EE7EB9BEB2B4DB7BC3D7F2F3DBB6328"),
    //        @ApiImplicitParam(name = "categoryName", value = "类型name", required = true, dataType = "String", paramType = "query", defaultValue = "墙"),
    //})
    //@GetMapping(value = "/packageDownloadFile", produces = "application/json;charset=UTF-8")
    //public JsonResult packageDownloadFile(@RequestParam String modeId,
    //                                      @RequestParam String categoryId,
    //                                      @RequestParam String categoryName, HttpServletResponse response) throws IOException {
    //
    //    //categoryId = "9EE7EB9BEB2B4DB7BC3D7F2F3DBB6328";
    //    //modeId = "A981DA7159A44C2EA56A18DC525A601C";
    //    //categoryName = "墙";
    //    List<ObjVo> objVoList = moFamilyTypeService.getObjVoList(categoryId, modeId);
    //    String filePath = FileUtil.packageDownloadFile(categoryName, response, objVoList);
    //    return ok("成功", filePath);
    //}
    @ApiOperation(value = "下载obj文件", notes = "通过族类型id，也就是主键id下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),
            @ApiImplicitParam(name = "familyTypeId", value = "类型id", required = true, dataType = "String", paramType = "query", defaultValue = "6E9AEFDF9EE2427BB2698AEDD43FACAE")
    })
    @GetMapping(value = "/downLoadObj", produces = "application/json;charset=UTF-8")
    public JsonResult downLoadObj(@RequestParam String modeId,
                                  @RequestParam String familyTypeId,
                                  HttpServletResponse response) throws IOException {

        MoFamilyType familyType = moFamilyTypeService.getFamilyType(familyTypeId, modeId);
        if (familyType != null) {
            if (StringUtils.isNotEmpty(familyType.getFilePath())) {
                String filePath = FileUtil.downLoadObj(familyType.getFilePath(), familyType.getName(), response);
                return ok("成功", filePath);
            }
        }
        return ok("文件路径已失效");
    }


    /**
     * 获取obj下所有元素
     *
     * @param familyTypeId
     * @return
     */
    @ApiOperation(value = "获取obj下所有元素", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "familyTypeId", value = "族类型id", required = true, dataType = "String", paramType = "query", defaultValue = "97816037F8B74902A23BFD7A356E35B7")
    })
    @GetMapping(value = "getElementListByFamilyTypeId")
    public JsonResult getElementListByFamilyTypeId(@RequestParam(value = "familyTypeId") String familyTypeId) {
        List<MoCompElement> compElementList = moFamilyTypeService.getElementListByFamilyTypeId(familyTypeId);
        for (MoCompElement moCompElement : compElementList) {
            String properties = moCompElement.getProperties();
            JSONObject jsonObject = JSON.parseObject(properties);
            moCompElement.setProperties(null);
            moCompElement.setPropertiesObject(jsonObject);
        }
        return ok("成功", compElementList);
    }

    /**
     * @Description:获取obj下某一个元素
     * @author 14258
     * @Author:xuyangyang
     * @Date: 2017/11/30
     */
    @ApiOperation(value = "获取obj下某一个元素", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "familyTypeId", value = "族类型id", required = true, dataType = "String", paramType = "query", defaultValue = "97816037F8B74902A23BFD7A356E35B7"),
            @ApiImplicitParam(name = "elementName", value = "元素name", required = true, dataType = "String", paramType = "query", defaultValue = "Object12079"),
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),

    })
    @GetMapping(value = "getElementByIdAndName")
    public JsonResult getElementByIdAndName(@RequestParam(value = "familyTypeId") String familyTypeId,
                                            @RequestParam(value = "modeId") String modeId,
                                            @RequestParam(value = "elementName") String elementName) {
        MoCompElement moCompElement = moFamilyTypeService.getElementByIdAndName(familyTypeId, modeId, elementName);
        JSONObject jsonObject = null;
        if (null != moCompElement) {
            String properties = moCompElement.getProperties();
            jsonObject = JSON.parseObject(properties);
            moCompElement.setProperties(null);
            moCompElement.setPropertiesObject(jsonObject);
        }
        return ok("成功", jsonObject);
    }

    /**
     * 单文件上传
     *
     * @param multiparFile
     * @return 文件保存路径
     */
    @ApiOperation(value = "单文件上传", notes = "")
    @PostMapping(value = "/uploadFile")
    public JsonResult uploadFile(@RequestParam("file") MultipartFile multiparFile) {
        String filePath = FileUtil.uploadfile(multiparFile);
        return ok("成功", filePath);
    }


    @ApiOperation(value = "减面后多文件上传", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件", required = true, dataType = "", paramType = "", defaultValue = " "),
            @ApiImplicitParam(name = "categoryId", value = "类型id", required = true, dataType = "String", paramType = "query", defaultValue = "0195E14B58254449BC0620737FD2675F"),
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),

    })
    @PostMapping(value = "/uploadSurfaceMultiFile")
    public JsonResult uploadSurfaceMultiFile(@RequestParam("files") MultipartFile[] files, String modeId, String categoryId, HttpServletRequest request) {

        //1. 读取上传文件临时目录，和生成文件临时目录
        uploadTempDir = uploadTempDir;
        generateTempDir = generateTempDir;

        //将文件删除
        //将文件删除
        //FileUtil.deleteDir(new File(uploadTempDir));
        //FileUtil.deleteDir(new File(generateTempDir));

        //2. 如果文件夹不存在，创建
        File file1 = new File(uploadTempDir);
        if (!file1.exists()) {

            file1.mkdirs();
        }
        File file2 = new File(generateTempDir);
        if (!file2.exists()) {
            file2.mkdirs();
        }

        //3. 保存上传文件到临时目录"e:/uploadTempDir"
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                FileUtil.saveFile(file, uploadTempDir);
            }
        }
        //4. 读取临时存放在本地的文件，执行减面
        File localFile = new File(uploadTempDir);
        File[] localFiles = localFile.listFiles();
        for (File file : localFiles) {
            String name = file.getName();
            String extName = name.substring(name.lastIndexOf(".") + 1);
            if ("obj".equals(extName)) {
                ExecuteExeUtil.exeWindows(name, uploadTempDir, generateTempDir);
            }
        }

        //5. 读取临时生成的文件，上传到服务器，
        File generateFile = new File(generateTempDir);
        //大小为0
        File[] generateFileArray = generateFile.listFiles();

        List<MoFamilyType> familyTypes = new ArrayList<>();
        for (int i = 0; i < generateFileArray.length; i++) {
            //如果是材质，则忽略
            String fileName = generateFileArray[i].getName();
            String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
            if ("mtl".equals(extName)) {
                continue;
            }
            //
            if (generateFileArray[i] != null) {
                MoFamilyType familyType = new MoFamilyType();
                String uploadFilePath = FileUtil.uploadfile(generateFileArray[i]);
                familyType.setCategoryId(categoryId);
                familyType.setModeId(modeId);
                familyType.setId(UUIDUtil.buildUUID());
                familyType.setFilePath(uploadFilePath);
                String name = (generateFileArray[i].getName());
                familyType.setName(name.substring(0, name.lastIndexOf(".")));
                familyType.setFamily(name);
                familyTypes.add(familyType);
            }
        }
        moFamilyTypeService.saveMoFamilyTypes(familyTypes);

        synchronized (this) {
            //将文件删除
            FileUtil.deleteDir(new File(uploadTempDir));
            FileUtil.deleteDir(new File(generateTempDir));
        }

        return ok("成功");
    }


    @ApiOperation(value = "多文件上传", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件", required = true, dataType = "", paramType = "", defaultValue = " "),
            @ApiImplicitParam(name = "categoryId", value = "类型id", required = true, dataType = "String", paramType = "query", defaultValue = "0195E14B58254449BC0620737FD2675F"),
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),
            @ApiImplicitParam(name = "flag", value = "根据平台不同选择数值，pc=3，ios=1，android=2", required = true, dataType = "String", paramType = "query", defaultValue = "1")

    })
    @PostMapping(value = "/uploadMultiFile")
    public JsonResult uploadMultiFile(@RequestParam("files") MultipartFile[] files,
                                      @RequestParam String modeId,
                                      @RequestParam String categoryId,
                                      @RequestParam String flag) {

        List<MoFamilyType> familyTypes = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            if (files[i] != null) {
                MoFamilyType familyType = new MoFamilyType();
                String filePath = FileUtil.uploadfile(files[i]);
                familyType.setCategoryId(categoryId);//2017-12-22
                familyType.setModeId(modeId);
                familyType.setId(UUIDUtil.buildUUID());
                familyType.setFilePath(filePath);
                familyType.setFlag(flag);
                String name = files[i].getOriginalFilename();
                familyType.setName(name.substring(0, name.lastIndexOf(".")));
                familyType.setFamily(name);
                familyTypes.add(familyType);
            }
        }
        moFamilyTypeService.saveMoFamilyTypes(familyTypes);
        return ok("成功");
    }

    /**
     * 打包下载zip(含处理obj)文件--(根据平台传入不同的参数，pc=3，ios=1，android=2)
     *
     * @param categoryId 类别id
     * @param modeId     模型id
     * @param response
     * @return 文件本地保存路径
     * @throws IOException
     */
    @ApiOperation(value = "打包下载zip文件,(含处理obj)文件", notes = "(根据平台传入不同的参数，pc=0，ios=1，android=2) ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),
            @ApiImplicitParam(name = "categoryId", value = "类型id", required = true, dataType = "String", paramType = "query", defaultValue = "0195E14B58254449BC0620737FD2675F"),
            @ApiImplicitParam(name = "flag", value = "", required = true, dataType = "String", paramType = "query", defaultValue = "0")

    })
    @GetMapping(value = "/packageDownloadObjFile", produces = "application/json;charset=UTF-8")
    public JsonResult packageDownloadObjFile(@RequestParam String modeId,
                                             @RequestParam String categoryId,
                                             @RequestParam String flag,
                                             HttpServletResponse response) throws IOException {
        List<ObjVo> objVoList = moFamilyTypeService.getObjVoList(categoryId, modeId, flag);
        String filePath = FileUtil.packageDownloadFile(modeId, categoryId, response, objVoList);
        return ok("成功", filePath);
    }

    /**
     * 打包下载zip(不含处理obj)文件--(根据平台传入不同的参数，pc=3，ios=1，android=2)
     *
     * @param categoryId 类别id
     * @param modeId     模型id
     * @param response
     * @return 文件本地保存路径
     * @throws IOException
     */
    @ApiOperation(value = "打包下载zip文件，(不含处理obj)文件", notes = "(根据平台传入不同的参数，pc=0，ios=1，android=2)")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),
            @ApiImplicitParam(name = "categoryId", value = "类型id", required = true, dataType = "String", paramType = "query", defaultValue = "7C5ECCA19FF24DB09D4E2438DBBC4113"),
            @ApiImplicitParam(name = "flag", value = "", required = true, dataType = "String", paramType = "query", defaultValue = "2")
    })
    @GetMapping(value = "/packageDownloadU3dFile", produces = "application/json;charset=UTF-8")
    public JsonResult packageDownloadU3dFile(@RequestParam String modeId,
                                             @RequestParam String categoryId,
                                             @RequestParam String flag, HttpServletResponse response) throws IOException {
        List<MoFamilyType> moFamilyTypeList = moFamilyTypeService.getFamilyTypeListByCategoryIdOrFlag(categoryId, modeId, flag);
        String filePath = FileUtil.packageDownloadU3dFile(modeId, categoryId, response, moFamilyTypeList);
        return ok("成功", filePath);
    }

    @ApiOperation(value = "上传 AssetSetBondle包，单个上传", notes = "(根据平台传入不同的参数，pc=0，ios=1，android=2)")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),
            @ApiImplicitParam(name = "categoryId", value = "类型id", required = true, dataType = "String", paramType = "query", defaultValue = "660F00B9BBE54F72934A5AB7573BD281"),
            @ApiImplicitParam(name = "flag", value = "分类", required = true, dataType = "String", paramType = "query", defaultValue = "1")
    })
    @PostMapping(value = "/uploadAssetBoundleFile")
    public JsonResult uploadAssetBoundleFile(@RequestParam("files") MultipartFile[] files, String modeId, String categoryId,String flag, HttpServletRequest request) {
		String uploadFilePath = FileUtil.uploadfile(files[0]);
		MoFamilyType familyType = new MoFamilyType();
		familyType.setCategoryId(categoryId);
		familyType.setModeId(modeId);
		familyType.setId(UUIDUtil.buildUUID());
		familyType.setFilePath(uploadFilePath);
		String name = (files[0].getOriginalFilename());
		familyType.setName(name.substring(0, name.lastIndexOf(".")));
		familyType.setFamily("IOS_TEST");
		familyType.setFlag(flag);
		List<MoFamilyType> familyTypes = new ArrayList<>();
		familyTypes.add(familyType);
		moFamilyTypeService.saveMoFamilyTypes(familyTypes);
        return ok("成功");
    }    
    
    /**
     * 打包下载zip(含处理obj)文件--(根据平台传入不同的参数，pc=3，ios=1，android=2)
     *
     * @param categoryId 类别id
     * @param modeId     模型id
     * @param response
     * @return 文件本地保存路径
     * @throws IOException
     */
    @ApiOperation(value = "打包AssetBoundle包文件", notes = "(根据平台传入不同的参数，pc=0，ios=1，android=2) ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modeId", value = "模型id", required = true, dataType = "String", paramType = "query", defaultValue = "A981DA7159A44C2EA56A18DC525A601C"),
            @ApiImplicitParam(name = "categoryId", value = "类型id", required = true, dataType = "String", paramType = "query", defaultValue = "660F00B9BBE54F72934A5AB7573BD281"),
            @ApiImplicitParam(name = "flag", value = "", required = true, dataType = "String", paramType = "query", defaultValue = "1")

    })
    @GetMapping(value = "/packageDownloadAssetBoundleFile", produces = "application/json;charset=UTF-8")
    public JsonResult packageDownloadAssetBoundleFile(@RequestParam String modeId,
                                             @RequestParam String categoryId,
                                             @RequestParam String flag,
                                             HttpServletResponse response) throws IOException {
        List<ObjVo> objVoList = moFamilyTypeService.getObjVoList(categoryId, modeId, flag);
        String filePath = FileUtil.packageDownloadAssetBoundleFile(modeId, categoryId, response, objVoList);
        return ok("成功", filePath);
    }   
}
