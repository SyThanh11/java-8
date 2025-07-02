package jetty;

import jetty.servlet.BlockingServlet;
import jetty.servlet.ThriftCallServlet;
import jetty.servlet.UserServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyServer {
    private Server server;

    public void start() throws Exception {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8081);
        server.setConnectors(new Connector[] {connector});

        // Register servlet
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(new ServletHolder(new BlockingServlet()), "/api/status");
        context.addServlet(new ServletHolder(new ThriftCallServlet()), "/api/thrift");
        context.addServlet(new ServletHolder(new UserServlet()), "/api/users");

        server.setHandler(context);

        server.start();
        System.out.println("Server started at http://localhost:8081/api/status");
        server.join();
    }

    public static void main(String[] args) throws Exception {
        new JettyServer().start();
    }
}
