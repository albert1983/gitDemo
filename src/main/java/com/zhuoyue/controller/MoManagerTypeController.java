package com.zhuoyue.controller;


import com.zhuoyue.bean.MoManagerType;
import com.zhuoyue.bean.MoStempInfo;
import com.zhuoyue.common.dto.JsonResult;
import com.zhuoyue.common.web.BaseController;
import com.zhuoyue.service.MoManagerTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 14258
 */
@Api(value = "API - MoManagerTypeController", description = "模型类型管理")
@RestController
@RequestMapping("/mcmanagertype/")
public class MoManagerTypeController extends BaseController {


    @Resource
    private MoManagerTypeService moManagerTypeService;


    /**
     * 获取管理类型列表成功
     *
     * @return
     */
    @ApiOperation(value = "获取管理类型列表成功", notes = "")
    @GetMapping("/getManagerTypeList")
    public JsonResult getManagerTypeList() {
        List<MoManagerType> moManagerTypeList = moManagerTypeService.getManagerTypeList();
        return ok("获取管理类型列表成功", moManagerTypeList);
    }


    /**
     * 获取阶段列表通过类型
     *
     * @param type
     * @return
     */
    @ApiOperation(value = "获取阶段列表通过类型", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "String", paramType = "query", defaultValue = "1")
    })
    @GetMapping("/getStempInfoByType")
    public JsonResult getStempInfoListByType(String type) {
        List<MoStempInfo> stempInfoList = moManagerTypeService.getStempInfoListByType(type);
        return ok("获取阶段列表通过类型成功", stempInfoList);
    }


}





