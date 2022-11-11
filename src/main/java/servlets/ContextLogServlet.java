package servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ContextLogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String par = request.getParameter("par1");

        ServletContext context = getServletContext();

        if (par == null || par.equals("")) {
            context.log("No message received: ", new IllegalStateException("Missing parameter"));
        } else {
            context.log("Here is the visitor message: " + par);
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Context log";
        String docType = "<!DOCTYPE html>\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<h2 align = \"center\">Messages sent</h2>\n" +
                "</body>" +
                "</html>"
      );
    }
}
