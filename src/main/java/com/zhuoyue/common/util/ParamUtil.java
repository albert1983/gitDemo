package com.zhuoyue.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuoyue.common.dto.JsonResult;
import com.zhuoyue.common.json.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ParamUtil {


    static Logger logger = Logger.getLogger(ParamUtil.class);


    /**
     * 获取 post 请求的内容
     *
     * @return
     * @author 作者: mazhao
     * @date 创建时间：下午2:19:08
     * @version 1.0
     * @parameter
     */
    public static JSONObject getRequestPostJson(HttpServletRequest request) throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        String str = new String(buffer, charEncoding);
        if (str == null || "".equals(str)) {
            return null;
        }
        JSONObject json = JSON.parseObject(str);
        //logger.info(JsonUtil.format(json.toString()));
        return json;
    }


    public static JSONArray getRequestPostJsonList(HttpServletRequest request) throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        String str = new String(buffer, charEncoding);
        JSONArray json = JSON.parseArray(str);
        //logger.info(JsonUtil.format(json.toString()));
        return json;
    }

    /**
     * 获取 post 请求的 byte[] 数组
     *
     * @return
     * @author 作者: mazhao
     * @date 创建时间：下午2:19:01
     * @version 1.0
     * @parameter
     */
    private static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        return IOUtils.toByteArray(request.getInputStream());
    }


    /**
     * 返回数据
     *
     * @return
     * @author 作者: mazhao
     * @date 创建时间：下午5:47:55
     * @version 1.0
     * @parameter
     */
    public static void returnData(Object param, HttpServletResponse response) {

        JsonResult jsonResult = new JsonResult(true, "", param);

        String json = JsonUtil.object2json(jsonResult);

        try {

            toPrintWriterJson(response, json);
        } catch (IOException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * @Author xuyangyang
     * @Describe 返回成功信息
     * @Date 2017/8/4
     * @Params a
     * @Return a
     */
    public static void returnSuccessMassage(String message, HttpServletResponse response) {

        JsonResult jsonResult = new JsonResult(true, message);
        String json = JsonUtil.object2json(jsonResult);
        try {
            toPrintWriterJson(response, json);
        } catch (IOException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 投出异常或错误提示
     *
     * @return
     * @author 作者: mazhao
     * @date 创建时间：下午5:20:26
     * @version 1.0
     * @parameter
     */
    public static void throwsQuestion(String message, HttpServletResponse response) {

        //Map<String, Object> map = new HashMap<String, Object>();
        //map.put("code", "1000");
        //map.put("data", "");
        //map.put("message", message);
        //JSONObject json = JSON.parseObject(String.valueOf(map));
        //try {
        //    toPrintWriterJson(response, json);
        //} catch (IOException e) {
        //    logger.info(e.getMessage());
        //    e.printStackTrace();
        //}
    }


    /**
     * @Author xuyangyang
     * @Describe 将输出流转换成json格式
     * @Date 2017/4/28
     * @Params a
     * @Return a
     */
    private static void toPrintWriterJson(HttpServletResponse response, String json) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json; charset=UTF-8");
        out.print(json);
        out.flush();
        out.close();
    }


}
