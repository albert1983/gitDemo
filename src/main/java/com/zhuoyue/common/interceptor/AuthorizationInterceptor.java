package com.zhuoyue.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhuoyue.common.annotation.Login;
import com.zhuoyue.common.exception.ServiceException;
import com.zhuoyue.common.util.JwtUtils;
import io.jsonwebtoken.Claims;
 

 
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtils jwtUtils;

    public static final String USER_KEY = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      
    	/*
    	
        //获取用户凭证
        String token = request.getHeader(jwtUtils.getHeader());
        if(StringUtils.isBlank(token)){
            token = request.getParameter(jwtUtils.getHeader());
        }

        //凭证为空
        if(StringUtils.isBlank(token)){
            throw new ServiceException(HttpStatus.UNAUTHORIZED.value()+" error ,    用户没登陆"  );
        }    	
        //获取用户凭证
        String deviceType = request.getHeader(jwtUtils.getDeviceType());
        if(StringUtils.isBlank(deviceType)){
        	deviceType = request.getParameter(jwtUtils.getDeviceType());
        }

        //凭证为空
        if(StringUtils.isBlank(deviceType)){
            throw new ServiceException(HttpStatus.UNAUTHORIZED.value()+" error,   设备类型不能为空"  );
        }     	
    	*/
	 /*	Login annotation;
		if (handler instanceof HandlerMethod) {
			annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
		} else {
			return true;
		}
		if (annotation != null) {
			return true;
		}
    	
		 //获取用户设备标识
        String deviceType = request.getHeader(jwtUtils.getDeviceType());
        if(StringUtils.isBlank(deviceType)){
        	deviceType = request.getParameter(jwtUtils.getDeviceType());
        }
        if(StringUtils.isBlank(deviceType)){
            throw new ServiceException(HttpStatus.BAD_REQUEST.value()+" error,  请求参数错误"  );
        }
		
        //获取用户凭证
        String token = request.getHeader(jwtUtils.getHeader());
        if(StringUtils.isBlank(token)){
            token = request.getParameter(jwtUtils.getHeader());
        }
        //凭证为空
        if(StringUtils.isBlank(token)){
            throw new ServiceException(HttpStatus.UNAUTHORIZED.value()+" error,  用户未登陆"  );
        }

        Claims claims = jwtUtils.getClaimByToken(token);
        if(claims == null || jwtUtils.isTokenExpired(claims.getExpiration())){
            throw new ServiceException(HttpStatus.UNAUTHORIZED.value()+"  error,  认证过期，请重新登录"  );
        } 
*/
        //设置userId到request里，后续根据userId，获取用户信息
        //request.setAttribute(USER_KEY, Long.parseLong(claims.getSubject()));

        return true;
    }
}
