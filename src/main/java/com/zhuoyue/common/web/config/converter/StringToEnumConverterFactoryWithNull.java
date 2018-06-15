package com.zhuoyue.common.web.config.converter;

import com.zhuoyue.common.util.ReflectUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * @author gzd
 * @date 2017/10/27 9:59
 * @desc 当字符转换为枚举错误时, 返回 null, 默认情况下是抛出 IllegalArgumentException
 */
public class StringToEnumConverterFactoryWithNull implements ConverterFactory<String, Enum> {

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> aClass) {
        Class<?> enumType = aClass;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }
        if (enumType == null) {
            throw new IllegalArgumentException("目标类型: " + aClass.getName() + " 不能转换成枚举类");
        }
        return new StringToEnum(enumType);
    }


    private class StringToEnum<T extends Enum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            if (source == null || source.trim().length() == 0) return null;
            source = source.trim();

            for (T obj : enumType.getEnumConstants()) {
                // 如果传递过来的是枚举名, 且能匹配上则返回
                if (source.equalsIgnoreCase(obj.name())) return obj;

                // 如果传递过来的值跟枚举里面的 code 相同则返回
                Object code = ReflectUtil.handleMethod(obj, "getCode");
                if (code != null && source.equalsIgnoreCase(code.toString())) return obj;
            }
            return null;
        }
    }

}
