package jetty.servlet;

import com.thrift.impl.CrossPlatformResource;
import com.thrift.impl.CrossPlatformService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/thrift-call")
public class ThriftCallServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");

        try (TTransport transport = new TSocket("localhost", 9090)) {
            transport.open();

            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            CrossPlatformService.Client client = new CrossPlatformService.Client(protocol);

            // Call Thrift method
            List<CrossPlatformResource> result = client.getList();

            resp.getWriter().println("{ \"result\": \"" + result + "\" }");

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().println("{ \"error\": \"" + e.getMessage() + "\" }");
        }
    }
}
