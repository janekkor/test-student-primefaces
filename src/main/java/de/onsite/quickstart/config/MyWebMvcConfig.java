package de.onsite.quickstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig {

    @Bean
    public WebMvcConfigurer  forwardToDefaultPage() {
        return new WebMvcConfigurer () {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName(
                        "forward:/studentList.xhtml");
                registry.addViewController("/students").setViewName(
                        "forward:/studentList.xhtml");
                registry.addViewController("/items").setViewName(
                        "forward:/itemList.xhtml");
            }
        };
    }
}