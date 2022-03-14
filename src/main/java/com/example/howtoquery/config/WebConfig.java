package com.example.howtoquery.config;

import com.example.howtoquery.service.ProgrammaticallyValidatingService;
import com.example.howtoquery.utils.resolver.MyPageableHandlerMethodArgumentResolver;
import com.example.howtoquery.utils.resolver.PatchRequestArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableSpringDataWebSupport
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    ProgrammaticallyValidatingService programmaticallyValidatingService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add( new MyPageableHandlerMethodArgumentResolver());
        argumentResolvers.add( new PatchRequestArgumentResolver(programmaticallyValidatingService));
    }
}
