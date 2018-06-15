package com.zhuoyue.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuoyue.bean.CadDesignPaper;
import com.zhuoyue.bean.ProDocFile;
import com.zhuoyue.common.dto.JsonResult;
import com.zhuoyue.common.exception.ServiceException;
import com.zhuoyue.common.web.BaseController;
import com.zhuoyue.service.ProDocFileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "API - ProDocFileController", description = "文档接口")
@RestController
@RequestMapping("/app/proDoc")
public class ProDocFileController extends BaseController {

	 
	@Autowired
	private ProDocFileService  proDocFileService  ;

 
	@ApiOperation(value = "插入 文档　操作", notes = "")
	@PostMapping("/insertProDoc")
	public JsonResult insertProDoc(@RequestParam(value = "file", required = false) MultipartFile multiparFile, HttpServletRequest request ,ProDocFile record ) {
		
		
		String fileName = multiparFile.getOriginalFilename(); 
		record.setDocFileName(fileName);
		String docFileExte = "" ; 
		if(fileName.contains(".doc")){
			docFileExte = "1";
		}
		if(fileName.contains(".xls") || fileName.contains(".xltm")){
			docFileExte = "2";
		}
		if(fileName.contains(".pdf")){
			docFileExte = "3";
		}
		if(fileName.contains(".ppt") || fileName.contains(".pot")){
			docFileExte = "4";
		}
		record.setDocFileExte(docFileExte);
		DecimalFormat fmt = new DecimalFormat("##0.0");  
		Double doubSize = (double) multiparFile.getSize()/(1024*1024); 
		record.setDocFileSize(fmt.format(doubSize )+"M");
		record.setCreateTime(new Date());
	 
		try {
			record.setDocFile(multiparFile.getBytes()); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		int resultInt = proDocFileService.insertSelective(record);
		if(resultInt == 1){
			return ok("数据保存成功");
		}else{
			return fail("数据保存失败，请重试");
		}
	}
    @ApiOperation(value = "获取 文档 列表　", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectId", value = "项目编号", required = true, dataType = "String", paramType = "query", defaultValue = "111"),
            @ApiImplicitParam(name = "pageNum", value = "分页页号", required = true, dataType = "String", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, dataType = "String", paramType = "query", defaultValue = "5"),
            @ApiImplicitParam(name = "orderState", value = "排序状态 ：0：按文件名称排序  1：按时间排序", required = true, dataType = "String", paramType = "query", defaultValue = "0"),
            
    })	
	
	@PostMapping("/getProDocList")
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
 
		HashMap<String,String> attrMap = new HashMap<>();
		attrMap.put("projectId", projectId);
		attrMap.put("orderState", orderState);
	    List<ProDocFile> cadViewsList = proDocFileService.getProDocListByProCode(attrMap);
	  //  System.out.println("Total: " + ((Page) cadViewsList).getTotal());
		return ok("文档列表", new PageInfo(cadViewsList));
	}	
	
    
	@ApiOperation(value = "下载文档数据", notes = "")
	@GetMapping("/getDocData")
	public void getDocData( String id,  HttpServletResponse response) throws IOException {
		if(StringUtils.isBlank(id)   ){
			throw new ServiceException("参数不正确");  
		}
		InputStream inputStream = null  ; 
		ProDocFile proDoc =proDocFileService.selectByPrimaryKey(id);
		inputStream =  new ByteArrayInputStream(proDoc.getDocFile());
		response.setHeader("Content-Disposition", "attachment; filename=" + proDoc.getDocFileName() );
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
    
	@ApiOperation(value = "按名称搜索文档列表", notes = "")
	@PostMapping("/searchDocByName")
	public JsonResult searchCadByName( ProDocFile record ) {
		 
		List<ProDocFile> proDocList = proDocFileService.searchDocFileByName(record);
		return ok("按名称搜索文档列表",proDocList);
	}    
}
