package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.Date;

public class SessionTrackServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        Date creationTime = new Date(session.getCreationTime());

        Date lastAccessedTime = new Date(session.getLastAccessedTime());

        String title = "Welcome back to my website";
        Integer visitCount = 0;
        String visitCountKey = "visitCount";
        String userIDKey = "userID";
        String userID = "ABCD";

        if (session.isNew()) {
            title = "Welcome to my website";
            session.setAttribute(userIDKey, userID);
        } else {
            visitCount = (Integer) session.getAttribute(visitCountKey);
            visitCount++;
            userID = (String) session.getAttribute(userIDKey);
        }
        session.setAttribute(visitCountKey, visitCount);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String docType = "<!DOCTYPE html>\n";

        out.println(docType +
                "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +

                    "<body bgcolor = \"#f0f0f0\">\n" +
                        "<h1 align = \"center\">" + title + "</h1>\n" +
                        "<h2 align = \"center\">Session Infomation</h2>\n" +
                        "<table border = \"1\" align = \"center\">\n" +

                            "<tr bgcolor = \"#949494\">\n" +
                                "  <th>Session info</th><th>value</th>" +
                            "</tr>\n" +

                            "<tr>\n" +
                                "  <td>id</td>\n" +
                                "  <td>" + session.getId() + "</td>" +
                            "</tr>\n" +

                            "<tr>\n" +
                                "  <td>Creation Time</td>\n" +
                                "  <td>" + creationTime + "</td>\n" +
                            "</tr>\n" +

                            "<tr>\n" +
                                "  <td>Time of Last Access</td>\n" +
                                "  <td>" + lastAccessedTime + "</td>\n" +
                            "</tr>\n" +

                            "<tr>\n" +
                                "  <td>User ID</td>\n" +
                                "  <td>" + userID + "</td>\n" +
                            "</tr>\n" +

                            "<tr>\n" +
                                "  <td>Number of visits</td>\n" +
                                "  <td>" + visitCount + "</td>\n" +
                            "</tr>\n" +

                        "</table>\n" +
                    "</body>" +
                "</html>"
      );
    }
}
