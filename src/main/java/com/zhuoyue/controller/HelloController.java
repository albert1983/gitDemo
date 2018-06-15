package com.zhuoyue.controller;

import com.zhuoyue.bean.Hello;
import com.zhuoyue.bean.TestTable;
import com.zhuoyue.bean.enums.CityCodeEnum;
import com.zhuoyue.common.util.ObjectUtil;
import com.zhuoyue.common.util.ZipUtil;
import com.zhuoyue.common.dto.JsonResult;
import com.zhuoyue.common.exception.ServiceException;
import com.zhuoyue.common.log.annotation.ControllerLog;
import com.zhuoyue.common.web.BaseController;
import com.zhuoyue.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
/**
 * Created by gzd on 2017/10/3.
 */

/**
 * @author gzd
 * @date 2017/11/3 10:54
 * @desc 测试类
 */
@Api(value = "API - HelloController", description = "测试使用")

@RestController
@RequestMapping("/hello")
public class HelloController extends BaseController {


    @Autowired
    private HelloService helloService;

    @Autowired
    private ZipUtil zipUtil;


    @ApiOperation(value = "首页", notes = "")
    @GetMapping("")
    public ModelAndView index() {
        return new ModelAndView("model/index");
    }


    @ApiOperation(value = "hello1", notes = "")
    @GetMapping("/hello1")
    public ModelAndView index1() {
        return new ModelAndView("model/webgl_clipping");
    }

    @ApiOperation(value = "example1", notes = "")
    @GetMapping("/example1")
    public ModelAndView example1() {
        return new ModelAndView("model/example1");
    }


    @ApiOperation(value = "测试请求正确否？", notes = "")
    @GetMapping("/test")
    public JsonResult test() {
        Hello hello = new Hello();
        hello.setId(ObjectUtil.uuid())
                .setUserName("zh").setDeleted(true)
                .setCityCode(CityCodeEnum.BEIJING);
        helloService.insert(hello);

        try {
            zipUtil.zip("E:\\logs", "123.zip");
            zipUtil.deleteZipFile("123.zip");
        } catch (IOException e) {
            throw new ServiceException("文件压缩错误");
        }
        return ok("成功");
    }

    @ApiOperation(value = "测试请求正确否？", notes = "")
    @PostMapping("insert")
    //此处为记录AOP拦截Controller记录用户操作
    @ControllerLog(description = "删除用户")
    public Object insert() {
        Hello hello = new Hello();

        hello.setId(ObjectUtil.uuid())
                .setUserName("zh").setDeleted(true)
                .setCityCode(CityCodeEnum.BEIJING);
        helloService.insert(hello);
        return hello;
    }


    @ApiOperation(value = "测试请求正确否？", notes = "")
    @PostMapping("insert2")
    public Object insert2() {
        
    	TestTable tt = new TestTable();
    	tt.setId("123");
    	tt.setFileName("lizhiwei");
    	tt.setFiles("lizhiwei".getBytes());
    	helloService.insert2(tt);
    	
        return tt;
    }
    
    
}
