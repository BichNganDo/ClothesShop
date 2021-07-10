package main;

import servlets.user.ManageUserServlet;
import servlets.user.LoginUserServlet;
import servlets.user.EditUserServlet;
import servlets.user.AddUserServlet;
import servlets.categogy.ManageCategogyServlet;
import servlets.categogy.EditCateServlet;
import servlets.categogy.AddCateServlet;
import servlets.product.ManageProductServlet;
import servlets.api.LoginApiUserServlet;
import servlets.product.EditProductServlet;
import servlets.product.AddProductServlet;
import servlets.api.ApiUserServlet;
import servlets.api.ApiProductServlet;
import servlets.api.ApiCateServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new ManageProductServlet()), "/listproducts");
        context.addServlet(new ServletHolder(new ManageUserServlet()), "/listusers");
        context.addServlet(new ServletHolder(new ManageCategogyServlet()), "/listcate");

        context.addServlet(new ServletHolder(new AddCateServlet()), "/addcate");
        context.addServlet(new ServletHolder(new AddProductServlet()), "/addproduct");
        context.addServlet(new ServletHolder(new AddUserServlet()), "/adduser");
        context.addServlet(new ServletHolder(new EditProductServlet()), "/editproduct");
        context.addServlet(new ServletHolder(new EditUserServlet()), "/edituser");
        context.addServlet(new ServletHolder(new EditCateServlet()), "/editcate");
        context.addServlet(new ServletHolder(new LoginUserServlet()), "/login");

        //AddApiCateServlet
        context.addServlet(new ServletHolder(new ApiCateServlet()), "/api/categogy");
        context.addServlet(new ServletHolder(new ApiProductServlet()), "/api/product");
        context.addServlet(new ServletHolder(new ApiUserServlet()), "/api/user");
        context.addServlet(new ServletHolder(new LoginApiUserServlet()), "/api/login");

        ContextHandler resourceHandler = new ContextHandler("/static");
        String resource = "./public";
        if (!resource.isEmpty()) {
            resourceHandler.setResourceBase(resource);
            resourceHandler.setHandler(new ResourceHandler());
        }

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);

        server.setHandler(handlers);

        server.start();

        System.out.println("Server started");

        server.join();
    }
}
