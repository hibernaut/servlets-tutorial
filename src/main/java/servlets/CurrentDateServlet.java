package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;


public class CurrentDateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Display Current Date & Time";
        ZonedDateTime dateTime = ZonedDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E yyyy.MM.dd 'at' hh:mm:ss a zzz", Locale.US);
        String formatted = dateTime.format(dateFormat);
        String docType = "<!DOCTYPE html>\n";

        out.println(docType +
                "<html>\n" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>" + title + "</title>" +
                "</head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<h2 align = \"center\">" + formatted + "</h2>\n" +
                "</body>" +
                "</html>"
      );
    }
}
