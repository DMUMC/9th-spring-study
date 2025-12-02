package com.umc.mission.global.resolver;

import com.umc.mission.global.annotation.CheckPage;
import com.umc.mission.global.apiPayload.code.PageErrorCode;
import com.umc.mission.global.apiPayload.exception.GeneralException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CheckPageArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 처리할 파라미터인지 검사(@CheckPage 어노테이션이 붙어있는지 확인)
        return parameter.hasParameterAnnotation(CheckPage.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String pageString = request.getParameter("page");

        int page;

        try {
            // 값이 없으면 기본값 1로 처리
            page = (pageString == null) ? 1 : Integer.parseInt(pageString);
        } catch (NumberFormatException e) { // 숫자가 아닌 값이 들어왔을 때
            throw new GeneralException(PageErrorCode.BAD_REQUEST);
        }

        // 1미만의 값이 들어왔을 때
        if (page < 1) {
            throw new GeneralException(PageErrorCode.BAD_REQUEST);
        }

        return page - 1;
    }
}
