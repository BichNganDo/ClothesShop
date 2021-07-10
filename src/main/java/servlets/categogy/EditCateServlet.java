/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.categogy;

import com.google.gson.Gson;
import common.Config;
import entity.Categogy;
import entity.Product;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CateModel;
import model.ProductModel;
import templater.PageGenerator;

/**
 *
 * @author Ngan Do
 */
public class EditCateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("static_domain", Config.STATIC_DOMAIN);
        pageVariables.put("app_domain", Config.APP_DOMAIN);

   
        int idEdit = Integer.parseInt(request.getParameter("id"));
        Categogy categogy = CateModel.getCateById(idEdit);
        pageVariables.put("categogy", gson.toJson(categogy));
        
        
        
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().println(PageGenerator.instance().getPage("category/edit-cate.html", pageVariables));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
}
