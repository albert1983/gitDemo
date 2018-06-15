package com.zhuoyue.common.dto;

/**
 * @author gzd
 * @date 2017/10/8 22:00
 * @desc json返回的实体类
 */
public class JsonResult {

    /**
     * 返回码
     */
    public static enum Code {
        /**
         * 失败
         */
        FAIL(0),
        /**
         * 成功
         */
        SUCCESS(1),
        /**
         * 未登录
         */
        NO_LOGIN(10);

        int flag;

        Code(int flag) {
            this.flag = flag;
        }

        public int getFlag() {
            return flag;
        }

        public static int getFlag(boolean success) {
            return success ? SUCCESS.flag : FAIL.flag;
        }

        public static boolean contains(int code) {
            for (Code ce : values()) {
                if (ce.flag == code) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 成功标记. 0:失败, 1:成功, 10:未登陆
     */
    int code = Code.FAIL.getFlag();

    /**
     * 消息
     */
    String msg = "";

    /**
     * 数据. 如果是String或StringBuilder或StringBuffer类的话, 则认为里面的内容就是json字符串了
     */
    Object data;

    public JsonResult(boolean success) {
        this.code = Code.getFlag(success);
    }

    /**
     * 成功与否及信息
     */
    public JsonResult(boolean success, String msg) {
        this(success, msg, null);
    }

    /**
     * 成功与否, 信息 及 返回的数据
     */
    public JsonResult(boolean success, String msg, Object data) {
        this(Code.getFlag(success), msg, data);
    }

    private JsonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回码及信息
     */
    public JsonResult(int code, String msg) {
        this(code, msg, null);
    }

    /**
     * 返回码, 信息 及 返回的数据
     */
    public JsonResult(Code code, String msg, Object data) {
        this(code.getFlag(), msg, data);
    }

    public JsonResult setCode(Code code) {
        this.code = code.getFlag();
        return this;
    }

    public JsonResult setCode(int code) {
        // 只在里面的才能被赋值
        if (Code.contains(code)) {
            this.code = code;
        }
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }
}
