package com.zhuoyue.common.web.config;


import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.zhuoyue.common.interceptor.AuthorizationInterceptor;
import com.zhuoyue.common.json.JsonUtil_back;
import com.zhuoyue.common.web.config.converter.String2DateConverter;
import com.zhuoyue.common.web.config.converter.StringToEnumConverterFactoryWithNull;
import com.zhuoyue.common.web.config.converter.StringTrimAndEscapeConverter;

 

/**
 * @author gzd
 * @date 2017/10/4 20:38
 * @desc spring mvc 的集中配置
 */
@Component
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
    private AuthorizationInterceptor authorizationInterceptor;
   
	
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setPrettyPrint(true);
        converter.setObjectMapper(JsonUtil_back.OBJECT_MAPPER);
        converters.add(converter);
        super.configureMessageConverters(converters);
    } 

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new String2DateConverter());
        registry.addConverter(new StringTrimAndEscapeConverter());
        registry.addConverterFactory(new StringToEnumConverterFactoryWithNull());
        super.addFormatters(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        ;
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authorizationInterceptor).addPathPatterns("/app/**");
		super.addInterceptors(registry);
	}

    
    
    
}
