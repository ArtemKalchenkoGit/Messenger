import java.io.*;
import java.net.*;
import javax.swing.*;

class SampleClient implements Runnable {
    static String hostName = "192.168.0.194";
    static int port = 8888;
    MenuWindow menu = new MenuWindow();

    SampleClient() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        try (
                Socket socket = new Socket(hostName, port);
                OutputStream out = socket.getOutputStream();
        ) {
            SwingUtilities.invokeLater(new ThreadForSwing());
            while (true) {
                out.write(menu.history.getBytes());
                out.flush();
            }
        } catch (Exception e) {
            System.out.println("init error: " + e);
        } // вывод исключений
    }
    class ThreadForSwing implements Runnable {

        @Override
        public void run() {
            menu.createAndShowMainPage();
        }
    }
}




