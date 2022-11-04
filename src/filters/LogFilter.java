package filters;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {

        String testParam = filterConfig.getInitParameter("test-param");

        System.out.println("Test Parameter: " + testParam);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String ipAddress = request.getRemoteAddr();

        System.out.println("IP " + ipAddress + ", Time" + new Date().toString());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
