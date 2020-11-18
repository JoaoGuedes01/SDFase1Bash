import java.net.*;
import java.io.*;

public class client {

    public static void main(String[] args) {

        try {
            // Iniciar o ligação do Client com uma Socket ao mesmo Ip e porta do server
            System.out.println("Client Started...");

            // Socket LocalHost
            // Socket soc = new Socket("localhost", 5000);

            // Socket da Virtual Machine
            Socket soc = new Socket("167.99.128.209", 5000);

            // Iniciar um BufferReader para receber input do utilizador
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Iniciar um Buffer com InputStream para poder receber mensagens do server
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

            // Criar um OutputStream para poder enviar mensagens para o server (true =
            // autoFlush())
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

            // Prompt de UserInput e ler a string colocada pelo user
            System.out.println("Escolhe uma opcao: ");
            System.out.println("");
            System.out.println("");
            System.out.println(" (1) - Ping ao server");
            System.out.println("");
            System.out.println(" (2) - Retornar string Upper Case");
            System.out.println("");
            System.out.println(" (3) - Retornar os IPs do server e Client");
            System.out.println("");
            System.out.print("Resposta: ");
            String option = userInput.readLine();
            out.println(option);
            switch (option) {
                // Fazer um ping ao server
                case "1":
                    System.out.println(
                            "----------------------------------------------------------------------------------");
                    System.out.println("");
                    System.out.println("Pinging server...");
                    System.out.println("");
                    System.out.println("Server says: " + in.readLine());
                    System.out.println("");
                    System.out.println("Connection closed...");
                    System.out.println("");
                    System.out.println(
                            "----------------------------------------------------------------------------------");
                    break;
                // Devolver a string posta pelo user em Upper Case
                case "2":
                    System.out.println(
                            "----------------------------------------------------------------------------------");
                    System.out.println("");
                    System.out.println("Opcao 2: Retornar Upper Case");
                    System.out.println("");
                    System.out.println("");
                    System.out.print("String: ");
                    String msg = userInput.readLine();
                    out.println(msg);
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Server says: " + in.readLine());
                    System.out.println("");
                    System.out.println("Connection closed...");
                    System.out.println("");
                    System.out.println(
                            "----------------------------------------------------------------------------------");
                    break;

                case "3":
                    System.out.println(
                            "----------------------------------------------------------------------------------");
                    System.out.println("");
                    System.out.println("Opcao 3: Retornar o IP do servidor:");
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Server IP: " + in.readLine());
                    System.out.println("");
                    System.out.println("Client IP: " + in.readLine());
                    System.out.println("");
                    System.out.println("Connection closed...");
                    System.out.println("");
                    System.out.println(
                            "----------------------------------------------------------------------------------");
                    break;

                default:
                    System.out.println("A sua escolha nao consta nas opcoes");
                    break;
            }

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
