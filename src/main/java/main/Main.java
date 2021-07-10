package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

public class Main {

    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new ManageProductServlet()), "/listproducts");
        context.addServlet(new ServletHolder(new ManageUserSevlet()), "/listusers");

        context.addServlet(new ServletHolder(new AddProductServlet()), "/addproduct");
        context.addServlet(new ServletHolder(new AddUserServlet()), "/adduser");
        context.addServlet(new ServletHolder(new EditProductServlet()), "/editproduct");
        context.addServlet(new ServletHolder(new EditUserServlet()), "/edituser");
        context.addServlet(new ServletHolder(new LoginUserServlet()), "/login");
        
        context.addServlet(new ServletHolder(new AddApiProductServlet()), "/api/product");
        context.addServlet(new ServletHolder(new AddApiUserServlet()), "/api/user");
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
