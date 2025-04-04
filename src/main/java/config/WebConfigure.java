package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfigure {
    @Configuration
    public class Webconfigure implements WebMvcConfigurer {
        @Override
        public void addViewControllers(ViewControllerRegistry registry){
            registry.addViewController("/{spring:[a-zA-Z0-9\\-_]+}").setViewName("forward:/index.html");
        }
    }
}
