package com.example.dobee.global.resolver;

import com.example.dobee.global.annotation.CheckPage;
import com.example.dobee.global.code.PageErrorCode;
import com.example.dobee.global.exception.PageException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CheckPageArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CheckPage.class) &&
                parameter.getParameterType().equals(Integer.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String pageValue = webRequest.getParameter(parameter.getParameterName());
        if (pageValue == null) {
            return 1;
        }

        try {
            int page = Integer.parseInt(pageValue);
            if (page <= 0) {
                throw new PageException(PageErrorCode.INVALID_PAGE);
            }
            return page;
        } catch (NumberFormatException e) {
            throw new PageException(PageErrorCode.INVALID_PAGE);
        }
    }
}
