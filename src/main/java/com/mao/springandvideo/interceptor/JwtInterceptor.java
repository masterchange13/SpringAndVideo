package com.mao.springandvideo.interceptor;

import com.mao.springandvideo.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 获取请求中的 token
        String token = request.getHeader("Authorization");

        // 验证 token
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 去掉 "Bearer " 前缀
            if (JwtUtil.verifyToken(token)) {
                // Token 验证通过，继续请求
                return true;
            }
        }

        // Token 验证失败，返回 401 状态
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}

