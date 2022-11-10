package filters;

import javax.servlet.*;
import java.io.IOException;

public class SiteHitCounter implements Filter {

    private int hitCount;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        hitCount = 0;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        hitCount++;

        System.out.println("Site visits count: " + hitCount);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
