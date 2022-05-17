import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    ServerSocket server_socket;
    public String source;
    public int PORT = 6969;
    WebServer (int port, String source) {
        this.PORT = port;
        this.source = source;
    }
    public static void main(String[] args) throws Exception {
        int passedPort = Integer.parseInt(args[0]);
        
        WebServer web_server = new WebServer(passedPort, args[1]);
        web_server.runServer();

    }

    public void runServer() throws Exception {

        server_socket = new ServerSocket(PORT);
        System.out.println("Server started at "+server_socket.getInetAddress().getHostAddress() + ":" + PORT);

        processHTTPRequest();

    }

    public void processHTTPRequest() {

        while(true) {
            Socket socket = null;
            try {
                socket = server_socket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Connection connection = null;
            try {
                connection = new Connection(socket, source);
            } catch (Exception e) {
                e.printStackTrace();
            }
            connection.start();
        }

    }

}
