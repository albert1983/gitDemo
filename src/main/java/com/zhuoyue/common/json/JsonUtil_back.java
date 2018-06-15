package com.zhuoyue.common.json;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zhuoyue.common.date.DateFormatType;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author gzd
 * @date 2017/10/21 22:38
 * @desc json util
 */
public class JsonUtil_back {

    public static final ObjectMapper OBJECT_MAPPER = new MyObjectMapper();

    private static class MyObjectMapper extends ObjectMapper {
        public MyObjectMapper() {
            // registerModule(new GuavaModule())
            // 日期不用 utc 方式显示, utc 是一个整数值
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            // 不确定值的枚举返回 null
            configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            // 不确定的属性项上失败
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // 时间格式
            setDateFormat(new SimpleDateFormat(DateFormatType.YYYY_MM_DD_HH_MM_SS.getValue()));
            // null 不序列化
            //setSerializationInclusion(JsonInclude.Include.NON_NULL);

            // 调用枚举的 getCode 方法, 如果有值则返回, 否则使用枚举的 name 方法. 在 getCode 方法上标注 @JsonValue 实现与此相同
            /*registerModule(new SimpleModule().addSerializer(Enum.class, new JsonSerializer<Enum>() {
                @Override
                public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers)
                        throws IOException {
                    Object obj = ObjectUtil.handleMethod(value, "getCode");
                    if (obj != null) gen.writeNumber(obj.toString());
                    else gen.writeString(value.name());
                }
            }));*/
        }
    }

    /**
     * 对象转换成 json 字符串
     */
    public static String toJson(Object obj) {
        return toJson(OBJECT_MAPPER, obj);
    }

    private static String toJson(ObjectMapper objectMapper, Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("不能序列化对象为 json", e);
        }
    }

    /**
     * 　将 json 字符串转换为对象
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("将 json 转换为对象时异常, 数据是:" + json, e);
        }
    }

    /**
     * 将 json 字符串转换为指定的数组列表
     */
    public static <T> List<T> toList(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json,
                    OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException("将 json 转换为 list 时异常, 数据是:" + json, e);
        }
    }

    public static <T> List<T> toListOfMap(String json, Class<?> keyClass, Class<?> valueClass) {
        try {
            JavaType mapType = OBJECT_MAPPER.getTypeFactory().constructMapType(Map.class, keyClass, valueClass);
            return OBJECT_MAPPER.readValue(json,
                    OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, mapType));
        } catch (Exception e) {
            throw new RuntimeException("将 json 转换为 list 时异常, 数据是:" + json, e);
        }
    }

    /* 输出 jsonp 格式数据 */
    /*public static String toJsonP(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }*/


    // ========== 返回自定义属性 ==========

    private static final String CUSTOM_FILTER = "customFilter";

    @JsonFilter(CUSTOM_FILTER)
    private static class CustomFilterMixin {
    }

    /*
    // 只输出传入的属性, 级联时会无法生效
    public static String objectToJsonIncludeFields(Object obj, String... fields) {
        ObjectMapper objectMapper = new MyObjectMapper();

        objectMapper.addMixIn(Object.class, CustomFilterMixin.class);
        objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter(CUSTOM_FILTER,
                SimpleBeanPropertyFilter.filterOutAllExcept(fields)));

        return toJson(objectMapper, obj);
    }
    // 排除传入的属性, 级联时会无法生效
    public static String objectToJsonExcludeFields(Object obj, String... fields) {
        ObjectMapper objectMapper = new MyObjectMapper();
        objectMapper.addMixIn(Object.class, CustomFilterMixin.class);
        objectMapper.setFilterProvider(new SimpleFilterProvider().addFilter(CUSTOM_FILTER,
                SimpleBeanPropertyFilter.serializeAllExcept(fields)));

        return toJson(objectMapper, obj);
    }
    */

    /**
     * 参考: https://github.com/Antibrumm/jackson-antpathfilter<br>
     * <span style="color:red">只在必要的时候用!</span> 每次调用此方法都会生成一个 ObjectMapper, 因此性能会慢一些.<br>
     * <pre>
     * public class User {
     *   Long id;
     *   String name;
     *   String password;
     *   Msg info;
     * }
     * public class Msg {
     *   Long id;
     *   String name;
     * }
     * User user = new User(123l, "ruby", "encrypt-code", new Msg(890l, "abc123");
     *
     * // 输出 {"id":123,"name":"ruby","password":"encrypt-code","info":{"id":890,"name":"abc123"}}
     * toJsonWithField(user); // <span style="color:red">性能会差一些, 直接使用 toJson(obj) 就可以了!!!</span>
     *
     * // 输出 {"name":"ruby","info":{}}
     * toJsonWithField(user, "name", "info")
     *
     * // 输出 {"name":"ruby"}
     * toJsonWithField(user, "name", "info.name")
     *
     * // 输出 {"name":"ruby","info":{"name":"abc123"}}
     * toJsonWithField(user, "name", "phone", "info", "info.name")
     *
     * // 输出 {"id":123,"name":"ruby","info":{}}
     * toJsonWithField(user, "*", "-password")
     *
     * // 输出 {"id":123,"name":"ruby","info":{"id":890,"name":"abc123"}} <span style="color:red">尽量不要使用 * 通配符</span>
     * toJsonWithField(user, "**", "-password")
     *
     * // 输出 {"id":123,"name":"ruby","info":{"name":"abc123"}} <span style="color:red">尽量不要使用 * 通配符</span>
     * toJsonWithField(user, "**", "-password", "-info.id")
     * </pre>
     */
    /*public static String toJsonWithField(Object obj, String... fields) {
        ObjectMapper om = new MyObjectMapper();
        om.addMixIn(Object.class, CustomFilterMixin.class);
        om.setFilterProvider(new SimpleFilterProvider().addFilter(CUSTOM_FILTER, new AntPathPropertyFilter(fields)));
        return toJson(om, obj);
    }*/

    /**
     * 生成自定义的 json 字符对象, toJsonWithField 会将对象转换成 String, 在返回前端之前转成对象, 不然字符串会加上双引号.
     *
     */
    /*public static Object toJsonObjectWithField(Object obj, String... fields) {
        if (obj == null) return null;

        return toObject(toJsonWithField(obj, fields), Object.class);
    }*/

}
