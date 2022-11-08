package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

public class DeleteCookiesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie cookie = null;
        Cookie[] cookies = null;

        cookies = request.getCookies();

        PrintWriter out = response.getWriter();
        String title = "Reading Cookies Example";
        String docType = "<!DOCTYPE html>\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" );

        if (cookies != null) {
            out.println("<h2> Cookies Name and Value</h2>");

            for (Cookie value : cookies) {
                cookie = value;

                if(cookie.getName().equals("first_name")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    out.print("Deleted cookie : " + cookie.getName( ) + "<br/>");
                }
                out.print("Name : " + cookie.getName( ) + ",  ");
                out.print("Value: " + cookie.getValue( )+" <br/>");
            }
        } else {
            out.println("<h2>No cookies founds</h2>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
