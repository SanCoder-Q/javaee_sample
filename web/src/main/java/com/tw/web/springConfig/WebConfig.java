package com.tw.web.springConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by SanCoder on 7/11/15.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.tw.web.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver resolver =
//        new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/view/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }


//    @Bean
//    public MappingJackson2JsonView mappingJacksonJsonView() {
//        return new MappingJackson2JsonView();
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/lib/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/lib/css/");
        super.addResourceHandlers(registry);
    }

    @Bean
    @Autowired
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(MappingJackson2JsonView mappingJackson2JsonView) {
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        ArrayList<View> defaultViews = new ArrayList<View>();
        defaultViews.add(mappingJackson2JsonView);
        contentNegotiatingViewResolver.setDefaultViews(defaultViews);
        contentNegotiatingViewResolver.setOrder(4);
        return contentNegotiatingViewResolver;
    }

    @Bean
    public ContentNegotiationManagerFactoryBean contentNegotiationManager() {
        ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
        contentNegotiationManager.setDefaultContentType(MediaType.APPLICATION_JSON);
        return contentNegotiationManager;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setOrder(1);
        return internalResourceViewResolver;
    }

    @Bean
    public MappingJackson2JsonView mappingJacksonJsonView() {
        return new MappingJackson2JsonView();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.
                mediaType("text/html", MediaType.TEXT_HTML).
                mediaType("json", MediaType.APPLICATION_JSON);
    }
}
