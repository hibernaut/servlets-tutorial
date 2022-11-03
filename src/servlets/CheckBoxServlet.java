package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckBoxServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String title = "Reading Checkbox Data";
        String docType = "<!DOCTYPE html>\n";

        out.println(docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body bgcolor = \"#f0f0f0\">\n" +
                        "<h1 align = \"center\">" + title + "</h1>\n" +
                        "<ul>\n" +
                        "  <li><b>Maths Flag : </b>: "
                        + request.getParameter("maths") + "\n" +
                        "  <li><b>Physics Flag: </b>: "
                        + request.getParameter("physics") + "\n" +
                        "  <li><b>Chemistry Flag: </b>: "
                        + request.getParameter("chemistry") + "\n" +
                        "</ul>\n" +
                        "</body>" +
                "</html>"
        );
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
