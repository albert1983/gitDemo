package com.zhuoyue.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 * http://localhost:8080/swagger-ui.html
 * http://blog.didispace.com/springbootswagger2/
 * https://gumutianqi1.gitbooks.io/specification-doc/content/tools-doc/spring-boot-swagger2-guide.html
 *
 * @author 14258
 * @date 2017/11/2
 */
@Configuration  //通过@Configuration注解，让Spring来加载该类配置。再通过@EnableSwagger2注解来启用Swagger2
@EnableSwagger2
public class Swagger2 {


    /**
     * 通过createRestApi函数创建Docket的Bean之后，apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
     * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，本例采用指定扫描的包路径来定义，
     * Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //包下的类，生成接口文档
                .apis(RequestHandlerSelectors.basePackage("com.zhuoyue.controller"))
                //加了ApiOperation注解的类，生成接口文档
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 在完成了上述配置后，其实已经可以生产文档内容，但是这样的文档主要针对请求本身，
     * 而描述主要来源于函数等命名产生，对用户并不友好，我们通常需要自己增加一些说明来丰富文档内容。
     * 如下所示，我们通过@ApiOperation注解来给API增加说明、通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("模型服务器")
                .description("提供模型相关接口")
                .termsOfServiceUrl("http://localhost:9999/swagger-ui.html")
                .contact("lizw")
                .version("1.0.0")
                .build();
    }


}
