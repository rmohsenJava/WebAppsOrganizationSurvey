package com.blackstone.webappsorganizationsurvey;

import com.blackstone.webappsorganizationsurvey.dto.FormRequest;
import com.blackstone.webappsorganizationsurvey.interceptor.LoggingInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class WebAppsOrganizationSurveyApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(WebAppsOrganizationSurveyApplication.class, args);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(generateApiInfo());
    }

    private ApiInfo generateApiInfo() {

        return new ApiInfo("Blackstone EIT Organization Survey Form",
                "WebAppsOrganizationSurvey", "Version 1.0 - mw",
                "urn:tos", "rmohsen@blackstoneeit.com",
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
    }


    @Component
    @RequiredArgsConstructor
    public static class StringToFormConverter implements Converter<String, FormRequest> {

        private final ObjectMapper objectMapper;

        @Override
        @SneakyThrows
        public FormRequest convert(String source) {
            return objectMapper.readValue(source, FormRequest.class);
        }
    }
}
