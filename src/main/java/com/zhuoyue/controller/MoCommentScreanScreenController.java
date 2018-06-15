package com.zhuoyue.controller;


import com.zhuoyue.common.dto.JsonResult;
import com.zhuoyue.common.util.FileUtil;
import com.zhuoyue.common.web.BaseController;
import com.zhuoyue.service.MoCommentScreanScreenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 14258
 */
@Api(value = "API - MoCommentScreanScreenController", description = "截屏")
@RestController
@RequestMapping("/mcscreanscreen/")
public class MoCommentScreanScreenController extends BaseController {


    @Resource
    private MoCommentScreanScreenService moCommentScreanScreenService;


    @ApiOperation(value = "保存屏幕截图", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, dataType = "String", paramType = "form", defaultValue = "地铁图"),
            @ApiImplicitParam(name = "typeFileId", value = "类型id", required = true, dataType = "String", paramType = "form", defaultValue = "4"),
            @ApiImplicitParam(name = "des", value = "描述", required = false, dataType = "String", paramType = "form", defaultValue = "这是北京地铁图"),
            @ApiImplicitParam(name = "cameraSize", value = "模型旋转信息", required = false, dataType = "String", paramType = "form", defaultValue = "模型旋转信息"),
            @ApiImplicitParam(name = "modelRotation", value = "模型相机大小信息", required = false, dataType = "String", paramType = "form", defaultValue = "模型相机大小信息")
    })
    @PostMapping("/saveScreanScreen")
    public JsonResult saveScreanScreen(@RequestParam(value = "file", required = false) MultipartFile multiparFile, HttpServletRequest request) {

        String name = request.getParameter("name");
        String typeFileId = request.getParameter("typeFileId");
        String des = request.getParameter("des");
        String cameraSize = request.getParameter("cameraSize");
        String modelRotation = request.getParameter("modelRotation");

        if (null == multiparFile) {

            return fail("文件内容为空，请重新选择文件！");
        }
        String filePath = FileUtil.uploadfile(multiparFile);
        moCommentScreanScreenService.saveScreanScreen(name, typeFileId, des, cameraSize, modelRotation, filePath);
        return ok("上传文件成功", filePath);
    }


}







