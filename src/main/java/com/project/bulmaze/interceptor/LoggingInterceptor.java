package com.project.bulmaze.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class LoggingInterceptor implements HandlerInterceptor {
    private final Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {
        LOG.info(" --> PRE Handle {}", request);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request,
                           @NotNull HttpServletResponse response,
                           @NotNull Object handler, ModelAndView modelAndView) {
        LOG.info(" --> POST Handle {}", request);
        if(modelAndView != null){
            modelAndView.getModel().keySet()
                    .forEach(modelKey -> System.out.println(modelAndView.getModel().get(modelKey)));
        }    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request,
                                @NotNull HttpServletResponse response,
                                @NotNull Object handler, Exception ex) throws Exception {
        LOG.info(" --> AFTER Completion {}", request);
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
