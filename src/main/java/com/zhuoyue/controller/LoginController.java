package com.zhuoyue.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhuoyue.common.annotation.Login;
import com.zhuoyue.common.dto.JsonResult;
import com.zhuoyue.common.exception.ServiceException;
import com.zhuoyue.common.util.JwtUtils;
import com.zhuoyue.common.web.BaseController;
import com.zhuoyue.service.MoUserInfoService;
import com.zhuoyue.vo.MoUserInfoVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API - LoginController", description = "登陆")
@RestController
@RequestMapping("/app")
public class LoginController extends BaseController {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private MoUserInfoService moUserInfoService ;

	@ApiOperation(value = "登陆", notes = "")
	@PostMapping("/login")
	@Login
	public JsonResult login(MoUserInfoVo record) {
		// 验证 用户登录
		// long userId = userService.login(mobile, password);
		MoUserInfoVo userInfo = moUserInfoService.selectByUserPhone(record);
		if(userInfo == null ){
			throw new ServiceException("用户名，密码不正确");  
		}
		if(!userInfo.getPassword().equals(record.getPassword())){
			throw new ServiceException("用户名，密码不正确");  
		}
		// 生成token
		String tokenData = jwtUtils.generateToken(userInfo.getId());
		userInfo.setToken(tokenData);
		return ok("登陆成功",userInfo);
	}

	@ApiOperation(value = "自动登陆", notes = "")
	@PostMapping("/authLogin")
	public JsonResult authLogin(String token, HttpServletRequest request) {
		
		if(StringUtils.isEmpty(token)){
			token = request.getHeader(jwtUtils.getHeader());
		}
		String userId = jwtUtils.getClaimUserId(token);
		String newToken = jwtUtils.generateToken(userId);
		
		MoUserInfoVo userInfo = moUserInfoService.selectByUserById(userId);
		userInfo.setToken(newToken);
		return ok("自动登陆成功",userInfo);
	}	
	
	
	@ApiOperation(value = "注册", notes = "")
	@GetMapping("/register")
	public JsonResult index1() {
		return null;
	}

	@ApiOperation(value = "刷新token", notes = "")
	@PostMapping("/refreshToken")
	public JsonResult refreshToken(String token) {
		String refreshToken =  jwtUtils.refreshToken(token);
		return ok("刷新token成功",refreshToken);
	}

}
