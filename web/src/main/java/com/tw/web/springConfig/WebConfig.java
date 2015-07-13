package com.tw.web.springConfig;

import com.tw.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.jdbc.datasource.*;
import org.springframework.orm.hibernate4.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by SanCoder on 7/11/15.
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement // As <tx:annotation-config />
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

    @Bean
    public HibernateTransactionManager createTransactionManager(){
        HibernateTransactionManager transactionManager=new HibernateTransactionManager();
        transactionManager.setSessionFactory(createSessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalSessionFactoryBean createSessionFactory(){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
//        properties.setProperty("hibernate.current_session_context_class", "thread");
//        properties.setProperty("maxwait", "10000");
//        properties.setProperty("maxidel", "25");
//        properties.setProperty("minidel", "5");
//        properties.setProperty("hibernate.connection.pool_size", "10");
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setAnnotatedClasses(new Class<?>[]{User.class});
        localSessionFactoryBean.setDataSource(createDataSource());
        return localSessionFactoryBean;
    }

    @Bean
    public DriverManagerDataSource createDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.
                mediaType("text/html", MediaType.TEXT_HTML).
                mediaType("json", MediaType.APPLICATION_JSON);
    }
}
