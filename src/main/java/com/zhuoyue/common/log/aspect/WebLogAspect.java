package com.zhuoyue.common.log.aspect;


import com.zhuoyue.common.json.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 系统日志，切面处理类，拦截指定controller下所有请求，不使用自定义注解
 *
 * @author 14258
 */
@Order(5)
@Aspect
@Component
public class WebLogAspect {


    private Logger logger = LoggerFactory.getLogger(getClass());


    ThreadLocal<Long> startTime = new ThreadLocal<>();


    /**
     * controller下所有请求都统一打印日志
     */
    @Pointcut("execution(public * com.zhuoyue.controller..*.*(..))")
    public void webLog() {
    }


    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        try {
            //记录开始时间
            startTime.set(System.currentTimeMillis());
            // 接收到请求，记录请求内容
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


            // 记录下请求内容
            logger.info("=====前置通知开始=====");
            logger.info("Request URL : " + request.getRequestURL().toString());
            logger.info("Request Method: " + request.getMethod());
            logger.info("Request Name : " + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            logger.info("Request IP : " + request.getRemoteAddr());
            logger.info("Request Param : " + Arrays.toString(joinPoint.getArgs()));
            logger.info("=====前置通知结束=====");
        } catch (Exception e) {
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}", e.getMessage());
        }


    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
    	if(null == ret ){
    		logger.info("返回结果 :  操作结束 ");
    	}else{
            logger.info("返回结果 : " + ret);
            logger.info(JsonUtil.object2json(ret));
    	}
        logger.info("请求耗时 : " + (System.currentTimeMillis() - startTime.get()));
    }


    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
              /*========日志输出=========*/
            logger.info("=====异常通知开始=====");
            logger.info("异常代码:" + e.getClass().getName());
            logger.info("异常信息:" + e.getMessage());
            logger.info("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            logger.info("请求IP:" + request.getRemoteAddr());
            logger.info("请求参数 : " + Arrays.toString(joinPoint.getArgs()));
            logger.info("=====异常通知结束=====");
        } catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
         /*==========记录本地异常日志==========*/
        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), Arrays.toString(joinPoint.getArgs()));
    }


}
