package com.syg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: shiyugang
 * @Date: 2019/11/17 21:17
 */
@Configuration
public class SwaggerConfig {

    private final String version = "1.0";

    private final String title = "SpringBoot示例工程";

    private final String description = "API文档自动生成示例";

    private final String termsOfServiceUrl = "http://www.kingeid.com";

    private final String license = "MIT";

    private final String licenseUrl = "https://mit-license.org/";

    private final Contact contact = new Contact("calebman", "https://github.com/calebman", "chenjianhui0428@gmail.com");

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInf())
                .select().build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder().title(title).termsOfServiceUrl(termsOfServiceUrl).description(description)
                .version(version).license(license).licenseUrl(licenseUrl).contact(contact).build();

    }

}
