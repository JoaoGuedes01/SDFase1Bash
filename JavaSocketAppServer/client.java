import java.net.*;
import java.io.*;

public class client {

    public static void main(String[] args) throws IOException {

        // Socket da Virtual Machine
        final Socket soc = new Socket("localhost", 5500);

        new Thread() {
            @Override
            public void run() {
                try {
                    // Iniciar um Buffer com InputStream para poder receber mensagens do server
                    BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

                    while (true) {
                        String msg = in.readLine();
                        System.out.println(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        try {
            // Iniciar o ligação do Client com uma Socket ao mesmo Ip e porta do server
            System.out.println("Client Started...");

            // Iniciar um BufferReader para receber input do utilizador
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Criar um OutputStream para poder enviar mensagens para o server (true =
            // autoFlush())
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

            System.out.print("What's your name: ");

           
            out.println(userInput.readLine());
            

            while (true) {
                String msg = userInput.readLine();
                out.println(msg);
                if (msg.equalsIgnoreCase("::quit")) {
                    soc.close();
                    System.exit(0);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
