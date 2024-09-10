package org.project.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc  // Spring MVC 설정을 활성화
@ComponentScan(basePackages = "org.project.backend")  // 컨트롤러, 서비스, 레포지토리 스캔
public class AppConfig implements WebMvcConfigurer {

    // JSP 뷰 리졸버 설정
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");  // JSP 파일 경로
        resolver.setSuffix(".jsp");  // JSP 파일 확장자
        return resolver;
    }

    // 여기에 다른 설정도 추가할 수 있음 (MessageConverters, Interceptors 등)
}
