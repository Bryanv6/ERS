package controller;

import com.google.gson.Gson;
import model.Employee;
import service.ERSService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by bryanvillegas on 4/25/18.
 */
public class LoginController {
    //public ERSService ers = new ERSService();
    Employee emp;
   public void login(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
       String username = req.getParameter("email");
       String password = req.getParameter("password");
        //hellworld
       emp = ERSService.getEmp(username, password);

       if(emp == null) {

           res.sendRedirect("/MasterServlet/login");
       }
       else{
           HttpSession session = req.getSession();
           session.setAttribute("email", emp);
           boolean manager = emp.isManager();
           session.setAttribute("manager", new Boolean(manager));

           String json = new Gson().toJson(emp);
           res.getWriter().append("from login");
           res.setContentType("application/json");

           res.getWriter().append(json);
       }
   }

    public void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String json = new Gson().toJson(emp);

        resp.setContentType("application/json");
        resp.getWriter().append(json);
        /*if(session.getAttribute("email")==null) {
            req.getRequestDispatcher("html/empLogin.html").forward(req,resp);
        } else {
            //req.getRequestDispatcher("html/empHome.html").forward(req,resp);
            resp.sendRedirect("/MasterServlet/home");
        }*/
    }
}
