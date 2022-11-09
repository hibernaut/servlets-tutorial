package servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class FileUploadServlet extends HttpServlet {

    private String filePath;
    private static final int MAX_FILE_SIZE = 50 * 1024;
    private static final int MAX_MEM_SIZE = 4 * 1024;

    @Override
    public void init() throws ServletException {
        filePath = getServletContext().getInitParameter("file-upload");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (!isMultipart) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        factory.setSizeThreshold(MAX_MEM_SIZE);
        factory.setRepository(new File("C:\\temp"));

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(MAX_FILE_SIZE);

        try {
            List<FileItem> fileItems = upload.parseRequest(request);

            Iterator<FileItem> iterator = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");

            while (iterator.hasNext()) {
                FileItem item = iterator.next();

                if (!item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();

                    File file;
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }

                    item.write(file);

                    out.println("Uploaded Filename: " + fileName + "<br>");
                    out.println("Uploaded File Field Name: " + fieldName + "<br>");
                    out.println("Uploaded File Content Type: " + contentType + "<br>");
                    out.println("Is Uploaded File In Memory: " + isInMemory + "<br>");
                    out.println("Uploaded File Size In Bytes: " + sizeInBytes + "<br>");
                }

            }
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        throw new ServletException("GET method used with " +
                getClass( ).getName( )+": POST method required.");
    }
}
