
package servlets.categogy;

import com.google.gson.Gson;
import common.Config;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import templater.PageGenerator;

/**
 *
 * @author Ngan Do
 */
public class AddCateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("static_domain", Config.STATIC_DOMAIN);
        pageVariables.put("app_domain", Config.APP_DOMAIN);
        pageVariables.put("menu_active", "manage-category");
        
        pageVariables.put("aside_menu", PageGenerator.instance().getPage("aside-menu.html", pageVariables));
      

        response.setContentType("text/html; charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("category/add-cate.html", pageVariables));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
}
