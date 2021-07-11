package servlets.product;

import common.Config;
import helper.HttpHelper;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import model.AuthenUser;

public class ManageProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authenValue = HttpHelper.getCookie(request, "authen");
        String phone = AuthenUser.getAuthenCookie(authenValue);
        if (phone != null && !"".equals(phone)) {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("app_domain", Config.APP_DOMAIN);
            pageVariables.put("static_domain", Config.STATIC_DOMAIN);
            pageVariables.put("message", "hello word");

            response.setContentType("text/html;charset=UTF-8");
            
            response.getWriter().println(PageGenerator.instance().getPage("product/product.html", pageVariables));
            
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendRedirect(Config.APP_DOMAIN + "/login");
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
