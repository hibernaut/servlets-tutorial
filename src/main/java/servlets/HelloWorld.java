package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

public class HelloWorld extends HttpServlet {

    private String message;

    public void init() throws ServletException{
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    public void destroy() {
    }
}
