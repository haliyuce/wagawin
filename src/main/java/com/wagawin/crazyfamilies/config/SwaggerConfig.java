package com.wagawin.crazyfamilies.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName())).paths(PathSelectors.any())
                .build().apiInfo(generateApiInfo());
    }

    private ApiInfo generateApiInfo() {

        return new ApiInfo("HeavenHR Server Applicant Test Service",
                "This service is to check the technology knowledge of a server applicant for heavenhr.",
                "Version 1.0 - mw", "urn:tos", "admin@heavenhr.com", "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0");
    }

}
