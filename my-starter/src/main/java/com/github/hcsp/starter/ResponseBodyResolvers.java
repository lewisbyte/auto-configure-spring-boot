package com.github.hcsp.starter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * @program: root
 * @description: 注解处理器
 * @author: lewis
 * @create: 2021-02-24 00:21
 */
public class ResponseBodyResolvers implements ApplicationContextAware, HandlerMethodArgumentResolver, HandlerMethodReturnValueHandler {

    private RequestResponseBodyMethodProcessor delegate;

    private ApplicationContext context;


    public boolean supportsReturnType(MethodParameter returnType) {
        // 支持自定义注解处理器
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), MyResponseBody.class) ||
                returnType.hasMethodAnnotation(MyResponseBody.class));
    }

    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }

    // 默认不支持请求参数解析
    public boolean supportsParameter(MethodParameter parameter) {
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return delegate.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        HandlerMethodReturnValueHandlerComposite bean = applicationContext.getBean(HandlerMethodReturnValueHandlerComposite.class);


        HandlerMethodReturnValueHandler obj = bean.getHandlers().stream().filter(c -> c.getClass().isAssignableFrom(RequestResponseBodyMethodProcessor.class))
                .findFirst().get();



    }
}