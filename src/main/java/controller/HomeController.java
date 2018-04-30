package controller;

import com.google.gson.Gson;
import model.Employee;
import org.eclipse.persistence.jaxb.MarshallerProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by bryanvillegas on 4/25/18.
 */
public class HomeController {

    public void goHome(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, JAXBException{
        HttpSession session = req.getSession();
        //PrintWriter printWriter = res.getWriter();

        Employee emp = (Employee)session.getAttribute("email");
        /*try {
            JAXBContext jc = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(emp, res.getWriter());
        }catch(JAXBException e){}*/

        if(emp == null)
            res.sendRedirect("/MasterServlet/login");
        else{

            if(session.getAttribute("email")==null) {
                res.sendRedirect("/MasterServlet/login");

            } else {
                String json = new Gson().toJson(emp);
                res.setContentType("application/json");

                //res.setCharacterEncoding("UTF-8");
                //res.sendRedirect("/html/empHome.html");
                //req.getRequestDispatcher("/html/empHome.html").forward(req,res);
                res.getWriter().append(json);


            }

        }
    }
}
