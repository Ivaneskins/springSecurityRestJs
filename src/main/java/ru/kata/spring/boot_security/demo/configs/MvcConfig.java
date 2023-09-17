package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user").setViewName("user");
    }


//    Настройка, позволяющая обойти ошибку CORS при обращении к localhost:8080 из других app
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/user/rest/**").allowedMethods("http://localhost:8080/")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE").allowedHeaders("*");
        registry.addMapping("/admin/rest/**").allowedMethods("http://localhost:8080/")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE").allowedHeaders("*");
    }
}
