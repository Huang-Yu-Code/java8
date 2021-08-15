package com.github.codingob.servlet.filter;

import com.github.codingob.servlet.util.AccountUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证过滤器
 *
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@WebFilter(filterName = "AuthFilter",urlPatterns = "/home/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getSession().getAttribute(AccountUtils.ATTRIBUTE_USERNAME) == null) {
            res.sendRedirect("login");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
