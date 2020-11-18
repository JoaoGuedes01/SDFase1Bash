import java.net.*;
import java.io.*;

public class ReqHandler extends Thread {
    Socket soc;
    BufferedReader in;
    PrintWriter out;

    public ReqHandler(Socket soc) {
        this.soc = soc;
        try {
            // Inicializar um Socket na thread criada pelo server, assim como atribuir cada
            // variável às que chegam do server
            this.in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            this.out = new PrintWriter(soc.getOutputStream(), true);
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            // Mensagem de ligação com sucesso
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Client conectado com sucesso");
            System.out.println("");
            System.out.println("Client IP: " + soc.getRemoteSocketAddress().toString().replace("/", ""));
            System.out.println("");
            String option = in.readLine();
            System.out.println("Client Option: " + option);
            switch (option) {
                // Opçao 1 - Fazer um ping ao server
                case "1":
                    out.println("Pinged received");
                    break;
                // Opçao 2 - Devolver a string posta pelo user em Upper Case
                case "2":
                    out.println(in.readLine().toUpperCase());
                    break;
                // Opçao 3 - Devolver a o IP do servidor
                case "3":
                    String ServerAddress = Inet4Address.getLocalHost().getHostAddress();
                    out.println(ServerAddress);
                    String ClientAddress = soc.getRemoteSocketAddress().toString().replace("/", "");
                    out.println(ClientAddress);
                    break;

                default:
                    break;
            }
            System.out.println("Connection closed...");
            System.out.println("");
            System.out.println("----------------------------------------------------------------------------------");

        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("Erro na execucao do servidor: " + e);
            System.exit(1);
        }
    }

}
