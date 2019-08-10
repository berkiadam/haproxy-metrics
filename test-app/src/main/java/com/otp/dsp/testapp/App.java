package com.otp.dsp.testapp;



import com.otp.dsp.testapp.service.TestAppService;
import com.otp.dsp.testapp.servlet.LivenessServlet;
import com.otp.dsp.testapp.servlet.ReadinessServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) throws Exception {


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        context.addServlet(new ServletHolder(new LivenessServlet()), "/liveness");
        context.addServlet(new ServletHolder(new ReadinessServlet()), "/readiness");


        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/test/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.packages",
                "io.swagger.jaxrs.listing," + TestAppService.class.getPackageName());

     



        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            logger.error("could not start server", e);

            try {
                jettyServer.stop();
            } catch (Exception e2) {

                logger.error("could not stop server", e2);
            }

        } finally {
            jettyServer.destroy();
        }


    }
}
