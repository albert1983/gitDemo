package com.zhuoyue.controller;


import com.zhuoyue.common.dto.JsonResult;
import com.zhuoyue.common.exception.NotFoundException;
import com.zhuoyue.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gzd
 * @date 2017/11/1 10:23
 * @desc webController的类似于aop的处理.
 * 包含了放入全局的model数据及全局的异常控制处理
 */
@ControllerAdvice
public class WebControllerAdvice {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 例子:给所有的controller都放入 curUser到model里去
     */
    /*@ModelAttribute("your key")
    public ICurUser addCurUser(HttpServletRequest request) {
        return (ICurUser) request.getAttribute(Const.CUR_USER_REQUEST_KEY);
    }*/

    /**
     * 404异常的处理
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public JsonResult notFound(NotFoundException e) {
        logger.info(e.getMessage());
        return new JsonResult(false, e.getMessage());
    }

    /**
     * 业务异常的处理
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public JsonResult serviceException(ServiceException e) {
        logger.info(e.getMessage());
        logger.info(e.getStackTrace().toString());
        return new JsonResult(false, e.getMessage());
    }

    /**
     * 未知的所有其他异常
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public JsonResult exception(Throwable e) {
        logger.warn(e.getMessage(), e);
        logger.error(e.getStackTrace().toString());
        return new JsonResult(false, "请求出现问题, 我们会尽快处理好");
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public JsonResult nullException(Throwable e) {
        logger.warn(e.getMessage(), e);
        logger.error(e.getStackTrace().toString());
        return new JsonResult(false, "空指针异常");
    }


}
