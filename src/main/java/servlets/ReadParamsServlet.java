package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.Enumeration;

public class ReadParamsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Reading All Form Parameters";
        String docType = "<!DOCTYPE html>\n";

        out.println(docType +
                        "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body bgcolor = \"#f0f0f0\">\n" +
                        "<h1 align = \"center\">" + title + "</h1>\n" +
                        "<table width = \"100%\" align = \"center\">\n" +
                        "<tr bgcolor = \"#949494\">\n" +
                        "<th>Param Name</th>" +
                "<th>Param Value(s)</th>\n"+
                        "</tr>\n"
        );

        Enumeration paramNames = request.getParameterNames();

        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            out.print("<tr><td>" + paramName + "</td>\n<td>");
            String[] paramValues = request.getParameterValues(paramName);

            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() == 0)
                    out.println("<i>No Value</i>");
                else
                    out.println(paramValue);
            } else {
                out.println("<ul>");

                for (String paramValue : paramValues) {
                    out.println("<li>" + paramValue);
                }
                out.println("</ul>");
            }
        }
        out.println("</tr>\n</table>\n</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
