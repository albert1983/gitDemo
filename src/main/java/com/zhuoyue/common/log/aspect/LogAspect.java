package com.zhuoyue.common.log.aspect;

import com.zhuoyue.common.log.annotation.ControllerLog;
import com.zhuoyue.common.log.annotation.ServiceLog;
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
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 系统日志，切面处理类，使用自定义注解
 * http://tiangai.iteye.com/blog/2103708
 *
 * @author 14258
 */
@Order(6)
@Aspect
@Component
public class LogAspect {


    /**
     * 本地异常日志记录对象
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();




    /**
     * Service层切点，使用自定义注解
     */
    @Pointcut("@annotation(com.zhuoyue.common.log.annotation.ServiceLog)")
    public void serviceAspect() {
    }

    /**
     * Controller层切点，使用自定义注解
     */
    @Pointcut("@annotation(com.zhuoyue.common.log.annotation.ControllerLog)")
    public void controllerAspect() {
    }



    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //HttpSession session = request.getSession();
        //读取session中的用户
        //User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
        try {
            //记录开始时间
            startTime.set(System.currentTimeMillis());

            //*========日志输出=========*//
            logger.info("=====前置通知开始=====");
            logger.info("Request URL : " + request.getRequestURL().toString());
            logger.info("Request Method : " + request.getMethod());
            logger.info("Request Method Name : " + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            logger.info("Method Des : " + getControllerMethodDescription(joinPoint));
            logger.info("Request IP : " + request.getRemoteAddr());
            logger.info("Request param : " + Arrays.toString(joinPoint.getArgs()));


            //*========数据库日志=========*//
            //Log log = SpringContextHolder.getBean("logxx");
            //log.setDescription(getControllerMethodDescription(joinPoint));
            //log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            //log.setType("0");
            //log.setRequestIp(ip);
            //log.setExceptionCode(null);
            //log.setExceptionDetail(null);
            //log.setParams(null);
            //log.setCreateBy(user);
            //log.setCreateDate(DateUtil.getCurrentDate());
            //保存数据库
            //logService.add(log);
            logger.info("=====前置通知结束=====");
        } catch (Exception e) {
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}", e.getMessage());
        }
    }


    @AfterReturning(returning = "ret", pointcut = "controllerAspect()")
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
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //HttpSession session = request.getSession();
        //读取session中的用户
        //User user = (User) session.getAttribute(WebConstants.CURRENT_USER);
        //获取请求ip
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params += joinPoint.getArgs()[i] + ";";
            }
        }
        try {
              /*========控制台输出=========*/
            System.out.println("=====异常通知开始=====");
            System.out.println("error Code :" + e.getClass().getName());
            System.out.println("error Info:" + e.getMessage());
            System.out.println("error method:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("error describe:" + getServiceMthodDescription(joinPoint));
            //System.out.println("请求人:" + user.getName());
            System.out.println("error IP:" + ip);
            System.out.println("request params:" + params);
            logger.info("请求参数 : " + Arrays.toString(joinPoint.getArgs()));
            
               /*==========数据库日志=========*/
            //Log log = SpringContextHolder.getBean("logxx");
            //log.setDescription(getServiceMthodDescription(joinPoint));
            //log.setExceptionCode(e.getClass().getName());
            //log.setType("1");
            //log.setExceptionDetail(e.getMessage());
            //log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            //log.setParams(params);
            //log.setCreateBy(user);
            //log.setCreateDate(DateUtil.getCurrentDate());
            //log.setRequestIp(ip);
            //保存数据库
            //logService.add(log);
            System.out.println("=====异常通知结束=====");
        } catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
         /*==========记录本地异常日志==========*/
        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);

    }


    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }


}
