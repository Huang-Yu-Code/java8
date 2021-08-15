package com.github.codingob.servlet.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * session监听器
 * @author codingob
 * @version 1.0.0
 * @since JDK1.8
 */
@WebListener
public class OnlineCountListener implements HttpSessionListener {
    private final static AtomicInteger ONLINE_COUNT = new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        servletContext.setAttribute("ONLINE_COUNT", ONLINE_COUNT.incrementAndGet());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        servletContext.setAttribute("ONLINE_COUNT", ONLINE_COUNT.decrementAndGet());
    }

}
