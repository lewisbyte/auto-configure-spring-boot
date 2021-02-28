package com.github.hcsp.starter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: root
 * @description: 注解处理器
 * @author: lewis
 * @create: 2021-02-24 00:21
 */
public class ResponseBodyResolvers implements ApplicationContextAware, HandlerMethodReturnValueHandler {

    private RequestResponseBodyMethodProcessor delegate;

    private ApplicationContext context;

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        // 支持自定义注解处理器
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), MyResponseBody.class) ||
                returnType.hasMethodAnnotation(MyResponseBody.class));
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {

        if (delegate==null) {
            // 获取处理器适配器bean
            RequestMappingHandlerAdapter bean = context.getBean(RequestMappingHandlerAdapter.class);
            // 从处理器适配器中拿到 RequestResponseBodyMethodProcessor 作为委托处理器
            this.delegate = (RequestResponseBodyMethodProcessor) bean.getArgumentResolvers().
                    stream().filter(c -> c.getClass().isAssignableFrom(RequestResponseBodyMethodProcessor.class))
                    .findFirst().get();
        }
        Map<Object,Object> map = new HashMap<>();
        map.put("status","ok");
        map.put("data",returnValue);
        delegate.handleReturnValue(map, returnType, mavContainer, webRequest);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        //此处初始化delegate会出现循环依赖
    }
}