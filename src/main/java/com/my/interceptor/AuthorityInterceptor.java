package com.my.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ccc016 on 2016/2/17.
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(AuthorityInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());

        log.info("requestUri:"+requestUri);
        log.info("contextPath:"+contextPath);
        log.info("url:"+url);

        Object user = request.getSession().getAttribute("user");
        if(user == null){
            log.info("Interceptor：redirect to the login page");
            request.setAttribute("message","please login first..");
            request.getRequestDispatcher("/WEB-INF/page/noAuthority.jsp").forward(request, response);

            return false;
        }else
            return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("==============执行顺序: 2、postHandle================");
        Object user = request.getSession().getAttribute("user");
        if(user != null){
            request.setAttribute("user",user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("==============执行顺序: 3、afterCompletion================");
    }
}
