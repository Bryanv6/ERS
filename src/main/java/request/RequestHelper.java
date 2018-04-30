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

    public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String switchString = req.getRequestURI().substring(req.getContextPath().length()+1);

        String name = req.getRequestURI();
        switch(name){
            case "/MasterServlet/home": if("POST".equals(req.getMethod())){
                hc.insertRequest(req, res);
            }else
                hc.goHome(req, res);
            break;
            case "/MasterServlet/login":if("POST".equals(req.getMethod())) {
                lc.login(req,res);
            }
            else
                lc.getPage(req,res);
            break;

            case "/MasterServlet/approve":
                hc.approveReq(req,res);
                break;
            //case "/MasterServlet/getRequests":

        }
    }
}
