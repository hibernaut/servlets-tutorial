package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RefreshServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setIntHeader("Refresh", 5);

        response.setContentType("text/html");

        Calendar calendar = new GregorianCalendar();
        String am_pm;
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        if (calendar.get(Calendar.AM_PM) == 0) {
            am_pm = "AM";
        } else {
            am_pm = "PM";
        }

        String CT = hour + ":" + minute + ":" + second + " " + am_pm;

        PrintWriter out = response.getWriter();
        String title = "Auto Refresh Header Setting";
        String docType = "<!DOCTYPE html>\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<p>Current Time is: " + CT + "</p>\n"
        );
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
