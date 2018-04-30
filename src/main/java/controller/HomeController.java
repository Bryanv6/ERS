package controller;

import com.google.gson.Gson;
import model.Employee;
import model.Requests;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import service.ERSService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.apache.log4j.Logger;
/**
 * Created by bryanvillegas on 4/25/18.
 */
public class HomeController {
    final static Logger Log = Logger.getLogger(HomeController.class);
    Employee emp;
    public void goHome(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        HttpSession session = req.getSession();
        //PrintWriter printWriter = res.getWriter();

        emp = (Employee)session.getAttribute("email");

        if(emp == null)
            res.sendRedirect("/MasterServlet/login");
        else{

            if(session.getAttribute("email")==null) {
                res.sendRedirect("/MasterServlet/login");

            } else {
                List<Requests> requests;
                if(emp.isManager()){
                    requests = ERSService.getAllEmpRequests();
                }
                else
                    requests = ERSService.getAllRequests(emp.getEmpID());
                //String json = "";

                String json = new Gson().toJson(requests);
                res.setContentType("application/json");
                Log.info("got requests");
                //res.setCharacterEncoding("UTF-8");
                //res.sendRedirect("/html/empHome.html");
                //req.getRequestDispatcher("/html/empHome.html").forward(req,res);
                res.getWriter().append(json);


            }

        }
    }

    public void insertRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String purpose = req.getParameter("purpose");
        String amount = req.getParameter("amount");

        ERSService.insertRequest(emp.getEmpID(), Double.parseDouble(amount), purpose);


    }
    public void getAllemp(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        HttpSession session = req.getSession();
        emp = (Employee)session.getAttribute("email");

        if(emp.isManager()){
            List<Employee> emps = ERSService.getAllEmps();
            if(emps == null){
                Log.debug("Could not get users.");
            }
            else {
                String json = new Gson().toJson(emps);
                res.setContentType("application/json");
                Log.info("Got employee");
                //res.setCharacterEncoding("UTF-8");
                //res.sendRedirect("/html/empHome.html");
                //req.getRequestDispatcher("/html/empHome.html").forward(req,res);
                res.getWriter().append(json);
            }
        }
    }
    public void approveReq(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("requestID"));

        List<Employee> allemps = ERSService.getAllEmps();
        Employee newemp = new Employee();
        if(allemps == null){
            System.out.println("Could not get users.");

        }
        else {
            for (Employee e : allemps) {
                if (e.getEmpID() == id) {
                    newemp = e;
                    break;
                }
            }
            if(newemp == null){
                System.out.println("Could not find user.");
            }
            else{
                if(ERSService.updateRequests(id,emp.getEmpID()))
                    System.out.println("Request has been approved.");
                else
                    System.out.println("Request was not approved.");
            }
        }
    }
}
