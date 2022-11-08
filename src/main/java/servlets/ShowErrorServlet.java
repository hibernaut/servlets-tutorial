package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class ShowErrorServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendError(407, "Need authentication!!!");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
