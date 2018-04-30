package request;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by bryanvillegas on 4/25/18.
 */
public class MasterServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private RequestHelper rh = new RequestHelper();

    public MasterServlet(){
        super();
    }
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
    }

    public void destroy() {
        // TODO Auto-generated method stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.service(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getRequestURI().substring(request.getContextPath().length())
                .startsWith("/html/")) {
            super.doGet(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
        rh.process(request, response);

        try {
            if(request.getAttribute("manager") == true){
                response.sendRedirect("/html/managerHome.html");
            }
            else
                response.sendRedirect("/html/empHome.html");
        }catch(NullPointerException e){}
    }

}
