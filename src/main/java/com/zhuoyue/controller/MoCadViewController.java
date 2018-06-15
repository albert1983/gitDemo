package com.zhuoyue.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuoyue.bean.CadDesignPaper;
import com.zhuoyue.bean.CadDesignType;
import com.zhuoyue.common.dto.JsonResult;
import com.zhuoyue.common.exception.ServiceException;
import com.zhuoyue.common.web.BaseController;
import com.zhuoyue.service.MoCadViewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import tk.mybatis.orderbyhelper.OrderByHelper; 

@Api(value = "API - MoCadViewController", description = "模型CAD图纸查看接口")
@RestController
@RequestMapping("/app/cadView")
public class MoCadViewController extends BaseController { 
	
	@Autowired
	private MoCadViewService moCadViewService; 
	
	@ApiOperation(value = "插入 CAD　图纸　操作", notes = "")
	@PostMapping("/insertCadView")
	public JsonResult inset(@RequestParam(value = "file", required = false) MultipartFile multiparFile, HttpServletRequest request ,CadDesignPaper record ) {
		
		record.setCadFileName(multiparFile.getOriginalFilename());
		DecimalFormat fmt = new DecimalFormat("##0.0");  
		Double doubSize = (double) multiparFile.getSize()/(1024*1024); 
		record.setCadFileSize(fmt.format(doubSize )+"M");
		record.setCreateTime(new Date());
		try {
			record.setCadFile(multiparFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		int resultInt = moCadViewService.insertSelective(record);
		if(resultInt == 1){
			return ok("数据保存成功");
		}else{
			return fail("数据保存失败，请重试");
		}
	}
	
	
	 
	
    @ApiOperation(value = "获取　CAD列表　", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目编号", required = true, dataType = "String", paramType = "query", defaultValue = "111"),
            @ApiImplicitParam(name = "pageNum", value = "分页页号", required = true, dataType = "String", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "String", paramType = "query", defaultValue = "5"),
            @ApiImplicitParam(name = "orderState", value = "排序状态 ：0：按文件名称排序  1：按时间排序", required = true, dataType = "String", paramType = "query", defaultValue = "0"),
            
    })	
	
	@PostMapping("/getCadViewList")
	public JsonResult getModeList( String projectId ,String orderState,String pageNum, String pageSize) {
		if(StringUtils.isEmpty(orderState)){
			orderState = "0" ;
		}
		if(StringUtils.isEmpty(pageNum)){
			pageNum = "1" ;
		}
		if(StringUtils.isEmpty(pageSize)){
			pageSize = "5" ;
		}
    	PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
		
		/*if(StringUtils.isNotEmpty(orderState)){
			if("1".equals(orderState)){
				OrderByHelper.orderBy("  order by create_time desc  ");
			}
			if("0".equals(orderState)){
				OrderByHelper.orderBy("  order by cad_file_name asc  ");
			}
		}*/
	    List<CadDesignPaper> cadViewsList = moCadViewService.getCadViewListByProCode(projectId,orderState);
	  //  System.out.println("Total: " + ((Page) cadViewsList).getTotal());
		return ok("模型列表", new PageInfo(cadViewsList));
	}	
	
 
	
	@ApiOperation(value = "获取（下载）CAD 图纸数据", notes = "")
	@GetMapping("/getCadData")
	public void getCadData( String id,  HttpServletResponse response) throws IOException {
		if(StringUtils.isBlank(id)   ){
			throw new ServiceException("参数不正确");  
		}
		InputStream inputStream = null  ; 
		 
		CadDesignPaper cadMode =moCadViewService.selectByPrimaryKey(id);
		
		inputStream =  new ByteArrayInputStream(cadMode.getCadFile());
		
		response.setHeader("Content-Disposition", "attachment; filename=" + cadMode.getCadFileName());
		OutputStream outStream = response.getOutputStream();
		int length;
		byte[] buffer = new byte[1024];
		while ((length = inputStream.read(buffer)) > 0) {
			outStream.write(buffer, 0, length);
		}
		// 这里主要关闭。
		outStream.flush();
		outStream.close();
		response.flushBuffer();
		inputStream.close();
		 
	}	
	
    @ApiOperation(value = "获取　CAD　分类　列表", notes = "")
	@PostMapping("/getCadTypeList")
	public JsonResult getCadTypeList() {
    	 
    	List<CadDesignType> cadTypesList = moCadViewService.getCadTypeList();
    	
    	return ok("cad Types list success",cadTypesList);
    	
    }
	   
    @ApiOperation(value = "根据 CAD类型 ，项目code , 获取相应的图纸列表", notes = "")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "projectCode", value = "项目编号", required = true, dataType = "String", paramType = "query", defaultValue = "111"),
        @ApiImplicitParam(name = "pageNum", value = "分页页号", required = true, dataType = "String", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "String", paramType = "query", defaultValue = "5"),
        @ApiImplicitParam(name = "orderState", value = "排序状态 ：0：按文件名称排序  1：按时间排序", required = true, dataType = "String", paramType = "query", defaultValue = "0"),
        @ApiImplicitParam(name = "cadTypeId", value = "类型分类", required = true, dataType = "String", paramType = "query", defaultValue = ""),
        
    })	    
	@PostMapping("/getCadDataByProCodeAndType")
	public JsonResult getCadDataByProCodeAndType( String projectId ,String orderState,String pageNum, String pageSize,String  cadTypeId) {
		if(StringUtils.isEmpty(orderState)){
			orderState = "0" ;
		}
		if(StringUtils.isEmpty(pageNum)){
			pageNum = "1" ;
		}
		if(StringUtils.isEmpty(pageSize)){
			pageSize = "5" ;
		}
    	PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));    	 
    	List<CadDesignPaper> pageList = moCadViewService.getCadViewListByProCodeAndType(projectId, orderState, cadTypeId);
    	return ok("模型列表",new PageInfo(pageList));
    }
    	
	@ApiOperation(value = "按名称搜索CAD列表", notes = "")
	@PostMapping("/searchCadByName")
	public JsonResult searchCadByName( CadDesignPaper record ) {
		 
		List<CadDesignPaper> cadViewsList = moCadViewService.searchCadByName(record);
		return ok("搜索模型列表",cadViewsList);
	}
	 	
}
