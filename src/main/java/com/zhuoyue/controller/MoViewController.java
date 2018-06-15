package com.zhuoyue.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhuoyue.common.dto.JsonResult;
import com.zhuoyue.common.exception.ServiceException;
import com.zhuoyue.common.web.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation; 

@Api(value = "API - LoginController", description = "模型列表显示及下载等功能 测试")
@RestController
@RequestMapping("/app/modeView")
public class MoViewController extends BaseController { 
	
	
	@ApiOperation(value = "获取模型列表", notes = "")
	@PostMapping("/getModeList")
	public JsonResult getModeList( String projectCode ) {
		if(StringUtils.isBlank(projectCode)){
			return this.fail("参数不正确");
		}
		
		List<Map<String,String>>  list = new ArrayList<>();
		Map<String,String> returnMap0 = new HashMap<String,String>();
		returnMap0.put("modeName", "中国尊识别的模型");
		returnMap0.put("createTime", "2018-06-11 11:23:14");
		returnMap0.put("modeSize", "14.5M");
		returnMap0.put("projectCode", "20180608");
		returnMap0.put("buildCode", "2018060801");		
		
 
		Map<String,String> returnMap2 = new HashMap<String,String>();
		returnMap2.put("modeName", "现代别墅(仅显示)");
		returnMap2.put("createTime", "2018-06-01 13:23:14");
		returnMap2.put("modeSize", "27.6M");
		returnMap2.put("projectCode", "20180529");
		returnMap2.put("buildCode", "2018052901");		
		Map<String,String> returnMap3 = new HashMap<String,String>();
		returnMap3.put("modeName", "现代建筑(仅显示)");
		returnMap3.put("createTime", "2018-05-29 13:23:14");
		returnMap3.put("modeSize", "1.19M");
		returnMap3.put("projectCode", "20180529");
		returnMap3.put("buildCode", "2018052902");
		list.add(returnMap0);
	 
		list.add(returnMap2);
		list.add(returnMap3);
		return ok("模型列表",list);
	}	
	
	
	@ApiOperation(value = "搜索模型列表", notes = "")
	@PostMapping("/searchModeByName")
	public JsonResult searchModeByName( String modeName ) {
		if(StringUtils.isBlank(modeName)){
			return this.fail("参数不正确");
		} 
		List<Map<String,String>>  list = new ArrayList<>();
		
		Map<String,String> returnMap0 = new HashMap<String,String>();
		returnMap0.put("modeName", "中国尊识别的模型");
		returnMap0.put("createTime", "2018-06-11 11:23:14");
		returnMap0.put("modeSize", "12.5M");
		returnMap0.put("projectCode", "20180608");
		returnMap0.put("buildCode", "2018060801");
		 
		Map<String,String> returnMap2 = new HashMap<String,String>();
		returnMap2.put("modeName", "现代别墅(仅显示)");
		returnMap2.put("createTime", "2018-06-01 13:23:14");
		returnMap2.put("modeSize", "27.6M");
		returnMap2.put("projectCode", "20180529");
		returnMap2.put("buildCode", "2018052901");		
		Map<String,String> returnMap3 = new HashMap<String,String>();
		returnMap3.put("modeName", "现代建筑(仅显示)");
		returnMap3.put("createTime", "2018-05-29 13:23:14");
		returnMap3.put("modeSize", "1.19M");
		returnMap3.put("projectCode", "20180529");
		returnMap3.put("buildCode", "2018052902");		
		
		
		if("中国樽识别的模型_new".contains(modeName)){
			list.add(returnMap0);
		}		
 
		if("现代别墅(仅显示)".contains(modeName)){
			list.add(returnMap2);
		}
		if("现代建筑(仅显示)".contains(modeName)){
			list.add(returnMap3);
		}		
		return ok("搜索模型列表",list);
	}
	 
	
	
	@ApiOperation(value = "获取模型楼层", notes = "")
	@PostMapping("/getModeFloorList")
	public JsonResult getModeFloorList( String projectCode ,String buildCode) {
		if(StringUtils.isBlank(projectCode) && StringUtils.isBlank(buildCode) ){
			return this.fail("参数不正确");
		}
		List<Map<String,String>>  list = new ArrayList<>();
		
		if(buildCode.equals("2018060801")  && "20180608".equals(projectCode)){
			Map<String,String> returnMap1 = new HashMap<String,String>();
			returnMap1.put("code", "201806080101");
			returnMap1.put("floorName", "地下三层");
			list.add(returnMap1);
		}		 
		
		if(buildCode.equals("2018052901")  && "20180529".equals(projectCode)){
			Map<String,String> returnMap1 = new HashMap<String,String>();
			returnMap1.put("code", "201805290101");
			returnMap1.put("floorName", "全部");
			list.add(returnMap1);
		}
		if(buildCode.equals("2018052902")  && "20180529".equals(projectCode)){
			Map<String,String> returnMap1 = new HashMap<String,String>();
			returnMap1.put("code", "201805290201");
			returnMap1.put("floorName", "全部");
			list.add(returnMap1);
		}		

		return ok("模型列表",list);
	}	
	
	@ApiOperation(value = "获取构件", notes = "")
	@PostMapping("/getModeSpecialList")
	public JsonResult getModeSpecialList( String projectCode ,String buildCode,String floorCode) {
		if(StringUtils.isBlank(projectCode) && StringUtils.isBlank(buildCode) ){
			return this.fail("参数不正确");
		}
		List<Map<String,String>>  list = new ArrayList<>();
	 

		if("2018060801".equals(buildCode) && "20180608".equals(projectCode) && "201806080101".equals(floorCode) ){
			
			Map<String,String> returnMap1 = new HashMap<String,String>();
			returnMap1.put("specialCode", "35");
			returnMap1.put("specialName", "建筑墙");
			Map<String,String> returnMap2 = new HashMap<String,String>();
			returnMap2.put("specialCode", "36");
			returnMap2.put("specialName", "建筑门");
			
			Map<String,String> returnMap3 = new HashMap<String,String>();
			returnMap3.put("specialCode", "39");
			returnMap3.put("specialName", "结构柱");
			Map<String,String> returnMap4 = new HashMap<String,String>();
			returnMap4.put("specialCode", "40");
			returnMap4.put("specialName", "结构梁");
			
			Map<String,String> returnMap5 = new HashMap<String,String>();
			returnMap5.put("specialCode", "41");
			returnMap5.put("specialName", "结构墙");
		 	
			Map<String,String> returnMap6 = new HashMap<String,String>();
			returnMap6.put("specialCode", "42");
			returnMap6.put("specialName", "结构板");
			
			
			Map<String,String> returnMap7 = new HashMap<String,String>();
			returnMap7.put("specialCode", "52");
			returnMap7.put("specialName", "暖通风管");
			
			Map<String,String> returnMap8 = new HashMap<String,String>();
			returnMap8.put("specialCode", "53");
			returnMap8.put("specialName", "暖通水管");
			/*Map<String,String> returnMap9 = new HashMap<String,String>();
			returnMap9.put("specialCode", "54");
			returnMap9.put("specialName", "给排水管");
			*/
			Map<String,String> returnMap10 = new HashMap<String,String>();
			returnMap10.put("specialCode", "55");
			returnMap10.put("specialName", "桥架");
		 
			list.add(returnMap1);
			list.add(returnMap2); 
			list.add(returnMap3);
			list.add(returnMap4); 
			list.add(returnMap5);
			list.add(returnMap6);
			list.add(returnMap7);
			list.add(returnMap8); 
			//list.add(returnMap9); 
			list.add(returnMap10);
		 
		}
		 		
		
		
		if(buildCode.equals("2018052901")  && "20180529".equals(projectCode)  && "201805290101".equals(floorCode) ){
			Map<String,String> returnMap1 = new HashMap<String,String>();
			returnMap1.put("specialCode", "01");
			returnMap1.put("specialName", "全部");
			list.add(returnMap1);
		}
		if(buildCode.equals("2018052902")  && "20180529".equals(projectCode) && "201805290201".equals(floorCode) ){
			Map<String,String> returnMap1 = new HashMap<String,String>();
			returnMap1.put("specialCode", "02");
			returnMap1.put("specialName", "全部");
			list.add(returnMap1);
		}		
		
		
		return ok("模型列表",list);
	}	
		
	@ApiOperation(value = "获取模型多楼层多专业", notes = "")
	@GetMapping("/getModeDatas")
	public void  getModeDatas( String projectCode ,String buildCode,String  type,HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(StringUtils.isBlank(projectCode) && StringUtils.isBlank(buildCode) ){
			 throw new ServiceException("参数不正确");  
		}
		InputStream inputStream = null  ; 
		
		File file = null ; 
		if("android".equals(type)){
			//inputStream = new FileInputStream(new File("/opt/data/20180509_2018050901_Android.zip"));
			//file = new File("E://20180509_2018050901_Android.zip");
			
			if(buildCode.equals("2018060801") && "20180608".equals(projectCode)){
				file = new File("/opt/data/20180608_2018060801_Android.zip");
				inputStream = new FileInputStream(file);
			}
			if(buildCode.equals("2018052901")  && "20180529".equals(projectCode)){
				file = new File("/opt/data/20180529_2018052901_Android.zip");
				inputStream = new FileInputStream(file);
			}
			
			if(buildCode.equals("2018052902")  && "20180529".equals(projectCode)   ){
				file = new File("/opt/data/20180529_2018052902_Android.zip");
				inputStream = new FileInputStream(file);
			}				
			
		} 
		if("ios".equals(type)){
			
			if(buildCode.equals("2018060801") && "20180608".equals(projectCode)){
				file = new File("/opt/data/20180608_2018060801_IOS.zip");
				inputStream = new FileInputStream(file);
			}			
			if(buildCode.equals("2018052901")  && "20180529".equals(projectCode)){
				file = new File("/opt/data/20180529_2018052901_IOS.zip");
				inputStream = new FileInputStream(file);
			}
			if(buildCode.equals("2018052902")  && "20180529".equals(projectCode)   ){
				file = new File("/opt/data/20180529_2018052902_IOS.zip");
				inputStream = new FileInputStream(file);
			}					
			
		}
		response.setCharacterEncoding(request.getCharacterEncoding());  
	    response.setContentType("application/octet-stream");  
		response.setHeader("Content-Disposition", "attachment; filename=" + projectCode +"_"+buildCode+ ".zip");
		response.setHeader("Content-Length", String.valueOf(file.length()));
		
		OutputStream outStream = response.getOutputStream();
		IOUtils.copy(inputStream,outStream);  
        response.flushBuffer();  
        outStream.close();
        if (inputStream != null) {  
            try {  
            	inputStream.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
	 
		 
	}
	
	@ApiOperation(value = "获取模型单楼层，单专业", notes = "")
	@GetMapping("/getModeData")
	public void  getModeData( String projectCode ,String buildCode,String floor ,String  special ,String  type, HttpServletResponse response) throws IOException {
		if(StringUtils.isBlank(projectCode) && StringUtils.isBlank(buildCode) ){
			throw new ServiceException("参数不正确");  
		}
		InputStream inputStream = null  ; 
		
		File file = null ; 
		if("android".equals(type)){
		 
			if(buildCode.equals("2018052901")  && "20180529".equals(projectCode)  && "201805290101".equals(floor) ){
				if("01".equals(special)){
					file = new File("/opt/data/20180529_2018052901_201805290101_01_Android.zip");
					inputStream = new FileInputStream(file);
				}
				
			}
			if(buildCode.equals("2018052902")  && "20180529".equals(projectCode)  && "201805290201".equals(floor) ){
				if("01".equals(special)){
					file = new File("/opt/data/20180529_2018052902_201805290201_01_Android.zip");
					inputStream = new FileInputStream(file);
				}
				
			}		
			
			//inputStream = new FileInputStream(new File("E://20180509_2018050901_IOS.zip"));
		} 
		if("ios".equals(type)){
			 
			if(buildCode.equals("2018052901")  && "20180529".equals(projectCode) && "201805290101".equals(floor)){
				if("01".equals(special)){
					file = new File("/opt/data/20180529_2018052901_201805290101_01_IOS.zip");
					inputStream = new FileInputStream(file);
				}
				
			}
			if(buildCode.equals("2018052902")  && "20180529".equals(projectCode) && "201805290201".equals(floor)){
				if("01".equals(special)){
					file = new File("/opt/data/20180529_2018052902_201805290201_01_IOS.zip");
					inputStream = new FileInputStream(file);
				}
				
			}			
			
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=" + projectCode +"_"+buildCode+ ".zip");
		response.setHeader("Content-Length", String.valueOf(file.length()));
		
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
	
	
	
}
