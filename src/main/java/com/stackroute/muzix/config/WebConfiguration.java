package com.stackroute.muzix.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class WebConfiguration {
  //Bean for h2ServletRegistration
    @Bean
    ServletRegistrationBean h2servletRegistration()
    {
        ServletRegistrationBean registrationBean=new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    //Bean for swagger documentation
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.stackroute.controller"))
                .paths(regex("/track.*"))
                .build();

    }

    //Application Event Listener
    @EventListener
    public void onApplicationEvent (ContextRefreshedEvent event) {
        System.out.print("context refreshed event fired: ");
        System.out.println(event);
    }
}
