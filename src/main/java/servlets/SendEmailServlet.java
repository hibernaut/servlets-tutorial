package servlets;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class SendEmailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = "john.smith@gmail.com";
        String applicationPassword = "dfsgfgfsgretete";
        String address1 = "frank.lloyd@gmail.com";
//        String address2 = "john.smith@gmail.com";
//        String address3 = "mary.peterson@gmail.com";

        Properties properties = new Properties();
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, applicationPassword);
            }
        };

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(properties, authenticator);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            MimeMessage message = new MimeMessage(session);
//            Address[] addresses = {new InternetAddress(address1),
//                    new InternetAddress(address2), new InternetAddress(address3)};

            message.setFrom(new InternetAddress(username));
//            message.addRecipients(Message.RecipientType.TO, addresses);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(address1));
            message.setSubject("This is the Subject Line!");

//            message.setText("This is actual message!");  // Sending text message
//            message.setContent("<h1>This is actual message</h1>", "text/html");  // Sending HTML content

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("This is message body");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();

            String filename = "file.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            String title = "Send Email";
            String result = "Sent message successfully....";
            String docType = "<!DOCTYPE html>\n";

            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor = \"#f0f0f0\">\n" +
                    "<h1 align = \"center\">" + title + "</h1>\n" +
                    "<p align = \"center\">" + result + "</p>\n" +
                    "</body>" +
                    "</html>"
            );
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
