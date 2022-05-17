import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HTTPResponse {

    HTTPRequest request;
    String response = null;
    // String source = "C:\\MSc\\SEM8\\PDC\\octoserver\\src";
    String source = "/home/sadham/java/octoserver/src";

    public HTTPResponse(HTTPRequest request, String src) {

        this.request = request;
        String CRLF = "\r\n";
        File file = new File(src + request.filename);
        response = "HTTP/1.1 200" + CRLF;
        response = response + "Server: Multi-Threaded Web Server/1.0" + CRLF;
        response = response + "Content-Type: text/html" + CRLF;
        response = response + "Connection: keep-alive" + CRLF;
        response = response + "Content-Length: " + file.length() + CRLF;
        response = response + CRLF;

        if(!file.exists()) {
            response = response.replace("200", "404"); //File not found
            System.out.println("Processing request - not found ");
            
        } else {
        try {

            FileInputStream file_input_stream = new FileInputStream(file);
            System.out.println(file_input_stream.available());
            // System.out.println("\n----Start of Response Frame----");

            int stream_char;
            try { 
                //Print contents of file to console
                while ((stream_char = file_input_stream.read()) != -1){
                    response = response + (char)stream_char;
                }
                System.out.println("Processing response in thread: " + Thread.currentThread().getId());
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
            Thread.sleep(3000);
            System.out.println("Response sent: Content-Length - " + file.length());

        } catch(FileNotFoundException e) {
            response = response.replace("200", "404"); //File not found
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
}