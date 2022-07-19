package com.lsy.config;

import io.swagger.annotations.Api;
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
 * Swagger使用的配置文件
 * Swagger地址 ： localhost:8080/swagger-ui.html
 *
 * 常用注解
 * @Api： 用于类，标识这个类是swagger的资源
 * @ApiIgnore： 用于类，忽略该 Controller，指不对当前类做扫描
 * @ApiOperation： 用于方法，描述 Controller类中的 method接口
 * @ApiParam： 用于参数，单个参数描述，与 @ApiImplicitParam不同的是，他是写在参数左侧的。如（ @ApiParam(name="username",value="用户名")Stringusername）
 * @ApiModel： 用于类，表示对类进行说明，用于参数用实体类接收
 * @ApiProperty：用于方法，字段，表示对model属性的说明或者数据操作更改
 * @ApiImplicitParam： 用于方法，表示单独的请求参数
 * @ApiImplicitParams： 用于方法，包含多个 @ApiImplicitParam
 * @ApiResponse： 用于方法，描述单个出参信息
 * @ApiResponses： 用于方法，包含多个@ApiResponse
 * @ApiError： 用于方法，接口错误所返回的信息
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //api基本信息的配置，信息会在api文档上显示，可有选择的填充，比如配置文档名称、项目版本号等
                .apiInfo(apiInfo())
                .select()
                //使用什么样的方式来扫描接口，RequestHandlerSelectors下共有五种方法可选。我们当前使用通过在类前添加@Api注解的方式，其他方法我们后续介绍。
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //扫描接口的路径，PathSelectors下有四种方法，我们这里是全扫，其他方法我们后续介绍。
                .paths(PathSelectors.any())
                .build();
    }

    //基本信息的配置，信息会在api文档上显示
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("mybatis-plus测试应用相关文档")
                .description("mp操作相关接口的文档")
                .termsOfServiceUrl("localhost:8080/swagger-ui.html")
                .version("1.0")
                .build();
    }
}
