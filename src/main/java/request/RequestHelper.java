package request;

import com.oracle.javafx.jmx.json.JSONException;
import controller.HomeController;
import controller.LoginController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by bryanvillegas on 4/25/18.
 */
public class RequestHelper {
    private LoginController lc = new LoginController();
    private HomeController hc = new HomeController();

    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, JAXBException {
        String switchString = req.getRequestURI().substring(req.getContextPath().length()+1);

        while(switchString.indexOf("/") > 0){
            switchString = switchString.substring(0, switchString.indexOf("/"));
        }
        /*PrintWriter pw = res.getWriter();
        pw.println();
        res.setContentType("text/html");
        String s1 = req.getParameter("email");
        String s2 = req.getParameter("password");
        pw.println("<html><body><div> "
                + "POST".equals(req.getMethod()) + " : " + req.getRequestURI() + ", are the values entered </div></body></html>");
        pw.close();*/
        String name = req.getRequestURI();
        switch(name){
            case "/MasterServlet/home": hc.goHome(req, res); break;
            case "/MasterServlet/login":if("POST".equals(req.getMethod())) {
                lc.login(req,res);
            }
            else
                lc.getPage(req,res);
            break;
            //default: hc.goHome(req, res); break;
        }
    }
}
