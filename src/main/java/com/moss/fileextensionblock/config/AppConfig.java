package com.moss.fileextensionblock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/dist/**").addResourceLocations("classpath:/static/dist/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/dist/fonts/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/dist/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/dist/img/");
        registry.addResourceHandler("/files/**").addResourceLocations("classpath:/static/dist/files/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.ico");
    }
}
