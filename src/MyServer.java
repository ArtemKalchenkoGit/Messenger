import java.io.*;
import java.net.*;
import java.util.TreeMap;

class MyServer {
    static final int PORT = 8888;
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Server Started");
        try {
            while (true) {
                Socket socket = s.accept();
                System.out.println("Connection has been created");
                try {
                    new SampleServer(socket);
                }
                catch (IOException e) {
                    socket.close();
                }
            }
        }
        finally {
            s.close();
        }
    }
}
class SampleServer extends Thread {

    private static String history = "<html>";
    private Socket socket;
    private InputStream in;
    byte[] buffer = new byte[1024];

    public SampleServer(Socket s) throws IOException {
        socket = s;
        in = socket.getInputStream();

        start();
    }

    ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
    String lastDataString = "";

    @Override
    public void run() {
        try {
            while (true) {
                int bytesRead = in.read(buffer);

                if (bytesRead > 0) {
                    dataStream.write(buffer, 0, bytesRead);
                    byte[] newData = dataStream.toByteArray();
                    String newDataString = new String(newData);

                    if (!newDataString.equals(lastDataString)) {
                        history = newDataString;
                        System.out.println("history has been changed: " + history);
                        lastDataString = newDataString;
                    }

                    dataStream.reset();
                }
            }
        }
        catch (IOException e) {
            System.err.println("IO Exception"+e);
        }
        finally {
            try {
                socket.close();
            }
            catch (IOException e) {
                System.err.println("Socket not closed"+e);
            }
        }
    }
    void checkMessage(){

    }
}
