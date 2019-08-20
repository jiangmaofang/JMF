package cn.com.jmf.learn.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

/**
 * Swagger2的接口配置
 *
 * @author zhouliangfei
 */
@Configuration
@EnableSwagger2
@ConditionalOnBean(SwaggerConfig.class)
public class SwaggerConfig {

    /**
     * 全局设置Content Type，默认是application/json
     * 如果想只针对某个方法，则注释掉改语句，在特定的方法加上下面信息
     * @ApiOperation(consumes="application/x-www-form-urlencoded")
     */
    public static final HashSet<String> consumes = new HashSet<String>() {{
        add("application/x-www-form-urlencoded");
    }};

    @Bean(value = "learnApi")
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("学习专用接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.com.jmf.learn.controller"))
                .paths(PathSelectors.any())
                .build()
                //.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.<SecurityScheme> newArrayList(apiKey()))
                .consumes(consumes);
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                .title("学习专用接口文档")
                .contact(new Contact("未知", null, null))
                .version("版本号:" + "未知")
                .build();
    }
}
