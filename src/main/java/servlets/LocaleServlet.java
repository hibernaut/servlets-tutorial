package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class LocaleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Locale locale = request.getLocale();
        String language = locale.getLanguage();
        String country = locale.getCountry();

        String date = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, locale)
                .format(new Date());

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String formattedCurrency = numberFormat.format(1000000);
        String formattedPercent = numberFormat.format(0.51);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String title = "Locale Example";
        String docType = "<!DOCTYPE html>\n";

        out.println(docType +
                "<html>\n" +
                "<head>" +
                "<title>" + title + "</title>" +
                "</head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h2 align = \"center\">Language: " + language + "</h2>\n" +
                "<h2 align = \"center\">Country: " + country + "</h2>\n" +
                "<h2 align = \"center\">Localized date example: " + date + "</h2>\n" +
                "<h2 align = \"center\">Localized currency example: " + formattedCurrency + "</h2>\n" +
                "<h2 align = \"center\">Localized percents example: " + formattedPercent + "</h2>\n" +
                "</body>"+
                "</html>"
      );
    }
}
