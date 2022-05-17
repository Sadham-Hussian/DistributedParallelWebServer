import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection extends Thread {

    Socket socket;
    PrintWriter print_writer;
    BufferedReader buffered_reader;
    String src;

    Connection(Socket socket, String src) throws Exception {
        this.src = src;
        this.socket = socket;
        InputStreamReader input_stream_reader = new InputStreamReader(this.socket.getInputStream());
        buffered_reader = new BufferedReader(input_stream_reader);
        print_writer = new PrintWriter(this.socket.getOutputStream());

    }

    public void run() {

        try {
            String request_frame = "";
            while(buffered_reader.ready() || request_frame.length() == 0) {
                request_frame = request_frame + (char)buffered_reader.read();
            }
            // System.out.print(request_frame);
            HTTPRequest request = new HTTPRequest(request_frame);
            System.out.println("GET " + request.filename);

            HTTPResponse response = new HTTPResponse(request, src);

            print_writer.write(response.response.toCharArray());

            print_writer.close();
            buffered_reader.close();
            socket.close();

        } catch(Exception e) {
        }

    }

}
