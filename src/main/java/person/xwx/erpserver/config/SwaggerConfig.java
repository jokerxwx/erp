package person.xwx.erpserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author: xwx
 * @date: 2022-12-06  19:28
 * @Description: TODO
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {
    /**
     * 配置基本信息
     * @return
     */
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("erp系统接口文档")
                .description("这是一个erp系统")
                .contact(new Contact("xwx","","2227517691@qq.com"))
                .version("1.0")
                .build();
    }

    @Bean
    public Docket restApi(){
        return new Docket(DocumentationType.OAS_30)

                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("person.xwx.erpserver.controller"))
                .build()
//                .securitySchemes(schemeList())
//                .securityContexts(securityContexts())
                ;
    }

}
