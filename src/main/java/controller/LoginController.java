package controller;

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
   public void login(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
       String username = req.getParameter("email");
       String password = req.getParameter("password");

       Employee emp = ERSService.getEmp(username, password);

       if(emp == null) {
           //res.getWriter().append("From login");
           //req.getRequestDispatcher("html/empHome.html").forward(req,res);
           res.sendRedirect("/MasterServlet/login");
       }
       else{
           HttpSession session = req.getSession();
           session.setAttribute("email", emp);
           //req.getRequestDispatcher("html/empHome.html").forward(req,res);
           //res.sendRedirect("/MasterServlet/home");
           res.getWriter().append("From login");
       }
   }

    public void getPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.getWriter().append("From login");
        resp.setContentType("text/plain");
        String hello = "Hello World";
        //res.setCharacterEncoding("UTF-8");
        resp.getWriter().write(hello);
        /*if(session.getAttribute("email")==null) {
            req.getRequestDispatcher("html/empLogin.html").forward(req,resp);
        } else {
            //req.getRequestDispatcher("html/empHome.html").forward(req,resp);
            resp.sendRedirect("/MasterServlet/home");
        }*/
    }
}
