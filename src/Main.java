import com.thrift.impl.CrossPlatformServiceServer;
import org.apache.thrift.transport.TTransportException;

public class Main {
    public static void main(String[] args) throws TTransportException {
        CrossPlatformServiceServer server = new CrossPlatformServiceServer();
        server.start();
    }
}