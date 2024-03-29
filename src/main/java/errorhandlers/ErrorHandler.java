package errorhandlers;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

public class ErrorHandler extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");

        if (servletName == null) {
            servletName = "Unknown";
        }

        String requestURI = (String) request.getAttribute("javax.servlet.error.request_uri");

        if (requestURI == null) {
            requestURI = "Unknown";
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Error/Exception Information";
        String docType = "<!DOCTYPE html>\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n");

        if (throwable == null && statusCode == null) {
            out.println("<h2>Error information is missing</h2>");
            out.println("Please return to the <a href=\"" +
                    response.encodeURL("http://localhost:8080/") +
                    "\">Home Page</a>.");
        } else if (statusCode != null) {
            out.println("The status code: " + statusCode);
        } else {
            out.println("<h2>Error information</h2>");
            out.println("Servlet Name : " + servletName + "</br></br>");
            out.println("Exception Type : " + throwable.getClass( ).getName( ) + "</br></br>");
            out.println("The request URI: " + requestURI + "<br><br>");
            out.println("The exception message: " + throwable.getMessage( ));
        }
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
