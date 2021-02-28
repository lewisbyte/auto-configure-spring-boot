package com.github.hcsp.starter;

import com.github.hcsp.starter.ResponseBodyResolvers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

/**
 * @program: root
 * @description: web mvc 配置
 * @author: lewis
 * @create: 2021-02-28 15:47
 */

@Configuration
public class WebMvnConf implements WebMvcConfigurer {

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(getResponseBodyResolvers());
    }

    @Bean
    public ResponseBodyResolvers getResponseBodyResolvers(){
        return new ResponseBodyResolvers();
    }

}