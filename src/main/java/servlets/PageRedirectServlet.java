package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class PageRedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String site = "/servlets_tutorial_war_exploded/currentdate";

        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
    }
}
