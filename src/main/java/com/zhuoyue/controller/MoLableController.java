package com.zhuoyue.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhuoyue.bean.MoLabel;
import com.zhuoyue.common.dto.JsonResult;
import com.zhuoyue.common.exception.ServiceException;
import com.zhuoyue.common.util.FileUtil;
import com.zhuoyue.common.web.BaseController;
import com.zhuoyue.service.MoLabelService;
import com.zhuoyue.vo.LabelVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author lizw
 */
@Api(value = "API - MoLableController", description = "模型标注，信息管理")
//@RestController
@Controller
@RequestMapping("/moLabel/")
public class MoLableController extends BaseController {


    @Autowired
    private MoLabelService moLabelService;

    /**
     * 保存标注
     *
     * @return
     */
    @ApiOperation(value = "保存标注信息", notes = "")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "audioFile", value = "标注语音", required = false, dataType = "String", paramType = "form", defaultValue = ""),
        @ApiImplicitParam(name = "imageFile", value = "上传的图片", required = false, dataType = "String", paramType = "form", defaultValue = "") 
    })        
    
    @ResponseBody
    @PostMapping("/saveMolable")
    public JsonResult saveMolable(@RequestParam(name="audioFile", required = false  ) MultipartFile  audioFile,@RequestParam(name="imageFile", required = false) MultipartFile  imageFile, @Valid  @ModelAttribute @ApiParam(name="moLabelForm" ,value="表单数据",required=false)  MoLabel moLabelForm) {
    	if(null !=  audioFile){
    		String audioFilePath = FileUtil.uploadfile(audioFile);
    		moLabelForm.setLabelAudioPath(audioFilePath);
    		moLabelForm.setLabelAudioName(audioFile.getOriginalFilename());
    	}
    	if(null != imageFile){
    		String imageFilePath = FileUtil.uploadfile(imageFile);
    		moLabelForm.setLabeleImgPath(imageFilePath);
    		moLabelForm.setLabeleImgName(imageFile.getOriginalFilename());
    	}
    	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateTimeStr = dateTimeFormatter.format(LocalDateTime.now());
    	moLabelForm.setLabelDate(dateTimeStr);
    	moLabelService.saveMoLabel(moLabelForm);
    	return this.ok("保存标注信息成功！") ;
    	
    }

    
    /**
     * 获取标注列
     *
     * @return
     */
    @ApiOperation(value = "获取标注信息列表", notes = "")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "projectCode", value = "项目编号", required = false, dataType = "String", paramType = "query", defaultValue = ""),
    	@ApiImplicitParam(name = "buildingCode", value = "建筑编号", required = false, dataType = "String", paramType = "query", defaultValue = ""),
    	@ApiImplicitParam(name = "floorCode", value = "楼层编号", required = false, dataType = "String", paramType = "query", defaultValue = ""),
    	@ApiImplicitParam(name = "specialtyCode", value = "专业编号", required = false, dataType = "String", paramType = "query", defaultValue = ""),
    	@ApiImplicitParam(name = "partCode", value = "构件编号", required = false, dataType = "String", paramType = "query", defaultValue = ""),
    })        
    @ResponseBody
    @PostMapping("/getMolableList")
    public JsonResult getMolableList(String projectCode ,String buildingCode,String floorCode,String specialtyCode,String partCode) {
    	
    	LabelVo labelVo  = new LabelVo();
    	labelVo.setProjectCode(projectCode);
    	labelVo.setBuildingCode(buildingCode);
    	labelVo.setFloorCode(floorCode);
    	labelVo.setSpecialtyCode(specialtyCode);
    	labelVo.setPartCode(partCode);
    	 
    	List<LabelVo>  labelVoList =   moLabelService.getLabelList(labelVo);
    	return this.ok("获取数据成功",labelVoList) ; 
    }
 
    	
    /**
     * 获取单个标注信息
     *
     * @return
     */
    @ApiOperation(value = "获取单个标注信息", notes = "")
    @ResponseBody
    @PostMapping("/getMolableContent")
    public JsonResult getMolableContent(@Valid  @ModelAttribute @ApiParam(name="labelVoForm" ,value="表单数据",required=true) LabelVo labelVo) {
		if(StringUtils.isEmpty(labelVo.getProjectCode()) && StringUtils.isEmpty(labelVo.getBuildingCode()) && StringUtils.isEmpty(labelVo.getFloorCode()) && StringUtils.isEmpty(labelVo.getSpecialtyCode()) && StringUtils.isEmpty(labelVo.getPartCode())  ){
			throw new ServiceException("参数不能为空");
		}
    	MoLabel entity = moLabelService.getMolableContent(labelVo);
    	return this.ok("获取数据成功",entity) ; 
    }
    
    /**
     * 获取单个标注信息
     *
     * @return
     */
    @ApiOperation(value = "获取单个标注信息图片", notes = "")
         
    @GetMapping("/getMolableImage")
    public void getMolableImage(@Valid  @ModelAttribute @ApiParam(name="labelVoForm" ,value="表单数据",required=true) LabelVo labelVo , HttpServletResponse response) {
		Map<String,String> resMap =  moLabelService.getMolableImage(labelVo);
    	try {
    		if(null == resMap || StringUtils.isEmpty(resMap.get("labeleImgPath"))  || StringUtils.isEmpty(resMap.get("labeleImgName")) ){
    			throw new ServiceException("查不到相应的图片信息");
    		}else{
    			FileUtil.downLoadImageOrAudio( resMap.get("labeleImgPath"),resMap.get("labeleImgName")  , response);
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }    
    
    /**
     * 获取单个标注信息语音
     *
     * @return
     */
    @ApiOperation(value = "获取单个标注信息语音", notes = "")
         
    @GetMapping("/getMolableAudio")
    public void getMolableAudio(@Valid  @ModelAttribute @ApiParam(name="labelVoForm" ,value="表单数据",required=true) LabelVo labelVo, HttpServletResponse response) {
		Map<String,String> resMap =  moLabelService.getMolableAudio(labelVo);
    	try {
    		if(null == resMap || StringUtils.isEmpty(resMap.get("labelAudioPath"))  || StringUtils.isEmpty(resMap.get("labelAudioName")) ){
    			throw new ServiceException("查不到相应的图片信息");
    		}else{
    			FileUtil.downLoadImageOrAudio( resMap.get("labelAudioPath"),resMap.get("labelAudioName")  , response);
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }      
    
    
    /**
     * 删除标注信息
     *
     * @return
     */
    @ApiOperation(value = "删除单个标注信息 ", notes = "")
    @ResponseBody
    @PostMapping("/deleteMoLable")
    public JsonResult deleteMoLable(@Valid  @ModelAttribute @ApiParam(name="labelVoForm" ,value="表单数据",required=true) LabelVo labelVo, HttpServletResponse response) {
 
  
		if(  StringUtils.isEmpty(labelVo.getLabelDate())   ){
			throw new ServiceException("时间参数不能为空");
		}
    	
    	 moLabelService.deleteMoLable(labelVo); 
    	 
    	 return this.ok("删除单个标注信息成功") ; 
    }         
    
    
}





