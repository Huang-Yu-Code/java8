# Servlet

* Tomcat
* Jetty
* Jboss
* WebLogic

## web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <welcome-file-list>
        <welcome-file>index</welcome-file>
    </welcome-file-list>
</web-app>
```

welcome-file: 首页

## servlet

依赖

```xml

<dependencies>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
    </dependency>
    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>javax.servlet.jsp-api</artifactId>
    </dependency>
    <dependency>
        <groupId>javax.servlet.jsp.jstl</groupId>
        <artifactId>jstl</artifactId>
    </dependency>
</dependencies>
```

注解配置`@WebServlet`

```java

@WebServlet("/xxx")
public class xxxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 转发
        req.getRequestDispatcher("jsp/index.jsp").forward(req, resp);
        // 重定向
        // resp.sendRedirect("jsp/index.jsp");
    }
}
```

xml配置

```xml

<web-app>
    ...
    <servlet>
        <servlet-name>xxx</servlet-name>
        <servlet-class>package.xxxServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>xxx</servlet-name>
        <url-pattern>/xxx</url-pattern>
    </servlet-mapping>
</web-app>
```

## filter

注解配置`@WebFilter("/xxx")`

```java

@WebFilter("/xxx")
public class xxxFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("过滤器初始化...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // 执行过滤器相关业务逻辑，最后是否放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁...");
    }
}
```

xml配置

```xml

<web-app>
    ...
    <filter>
        <filter-name>xxx</filter-name>
        <filter-class>package.xxxFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>xxx</filter-name>
        <url-pattern>/xxx</url-pattern>
    </filter-mapping>
</web-app>
```

## listener

注解配置`@WebListener`

```java

@WebListener
public class xxxListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // session创建时的业务逻辑
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // session销毁时的业务逻辑
    }
}
```

xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app>
    ...
    <listener>
        <listener-class>package.xxxListener</listener-class>
    </listener>
</web-app>
```

## utils

### Json

```xml

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.0-rc1</version>
</dependency>
```

### Upload

```xml

<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.4</version>
</dependency>
```

### SSL

```shell
keytool -genkey -alias tomcat -keyalg RSA -keystore D:\environment\java\apache-tomcat-9.0.52\conf
```

```xml
<!-- Define an SSL Coyote HTTP/1.1 Connector on port 8443 -->
<Connector
        protocol="org.apache.coyote.http11.Http11NioProtocol"
        port="8443" maxThreads="200"
        scheme="https" secure="true" SSLEnabled="true"
        keystoreFile="conf/.keystore" keystorePass="123456"
        clientAuth="false" sslProtocol="TLS"/>
```