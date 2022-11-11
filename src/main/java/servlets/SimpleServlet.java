package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/simple", initParams = {
        @WebInitParam(name = "foo", value = "Hello "),
        @WebInitParam(name = "bar", value = " World!")
})
public class SimpleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.print("<html><body>");
        out.print("<h3>Hello Servlet</h3>");
        out.println(getInitParameter("foo"));
        out.println(getInitParameter("bar"));
        out.print("</body></html>");
    }
}
