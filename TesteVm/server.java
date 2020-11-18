import java.net.*;
import java.io.*;

public class server {
    public static void main(String[] args) throws IOException {
        // Iniciar a ServerSocket
        System.out.println("Waiting fot clients...");
        ServerSocket ss = new ServerSocket(5000);

        while (true) {
            try {
                // Aceitar pedidos de ligação ao servidot
                Socket soc = ss.accept();

                // Iniciar uma thread para lidar com multiplos pedidos
                ReqHandler thread = new ReqHandler(soc);
                thread.start();

            } catch (IOException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

    }
}
