package com.restful.config;

import com.restful.filter.LoginFilter;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: restfulproject
 * @description: description
 * @author: lw
 * @create: 2018-11-08 15:25
 **/
@Configuration
public class ServletConfig implements WebMvcConfigurer {


    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter();
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
           registry.addInterceptor(loginFilter())
                   .addPathPatterns("/**")
                   .excludePathPatterns("/loginCtrl/login");
    }
}
