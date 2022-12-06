package top.jionjion.core.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义Servlet请求, 通过 ServletRegistrationBean 注册
 * <a href="http://127.0.0.1:8080/my-servlet">...</a>
 *
 * @author Jion
 */
public class MyHttpServlet extends HttpServlet {

    /**
     * @param request  请求
     * @param response 响应
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Web容器,处理请求...." + request.getRequestURI() + "  " + response.getStatus());
        response.getWriter().print("hello");
    }
}
