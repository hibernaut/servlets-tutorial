package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class DatabaseAccessServlet extends HttpServlet {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testservlet?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PSWD = "root";
    private static final String SELECT_ALL = "SELECT id, first_name, last_name, age FROM employees";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection connection = null;
        Statement statement = null;

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Database Result";
        String docType = "<!DOCTYPE html>\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n");

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Driver loading success!");

            connection = DriverManager.getConnection(DB_URL, USER, PSWD);
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SELECT_ALL);

            while (result.next()) {
                int id = result.getInt("id");
                int age = result.getInt("age");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                out.println("ID: " + id + ", Age: " + age + ", First Name: " + firstName + ", Last Name: " + lastName + "</br>");
            }

            out.println("</body></html>");

            result.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ignored) {
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
