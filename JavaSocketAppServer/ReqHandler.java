import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class ReqHandler extends Thread {
    Socket soc;
    BufferedReader in;
    PrintWriter out;
    String ClientName;
    private static final Map<String, ReqHandler> clients = new HashMap<String, ReqHandler>();

    public ReqHandler(Socket soc) {
        this.soc = soc;
        try {
            // Inicializar um Socket na thread criada pelo server, assim como atribuir cada
            // variável às que chegam do server
            this.in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            this.out = new PrintWriter(soc.getOutputStream(), true);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // Mensagem de ligação com sucesso
            System.out.println("----------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Client conectado com sucesso");
            System.out.println("");
            System.out.println("Client IP: " + soc.getRemoteSocketAddress().toString().replace("/", ""));
            System.out.println("");
            ClientName = in.readLine();
            clients.put(this.ClientName, this);
            System.out.println("Client Name: " + ClientName);
            out.println("Hi there, " + ClientName);

            while (true) {
                String msg = in.readLine();
                Boolean normal = true;
                if (msg.equalsIgnoreCase("::quit")) {
                    normal = false;
                    out.println("Connection Closed");
                    this.soc.close();
                    System.out.println("");
                    System.out.println("Connection Closed");
                    return;
                } else if (msg.toLowerCase().startsWith("::msg")) {
                    normal = false;
                    String destname = (String) msg.subSequence(5, msg.length());
                    System.out.println("sending msg to: " + destname);
                    ReqHandler dest = clients.get(destname);
                    if (dest == null) {
                        out.println("The client does not exist");
                    } else {
                        out.println("Escreve uma mensagem para " + dest.getClientName() + ": ");
                        dest.getOut().println(this.ClientName + ": " + in.readLine());
                        ;
                    }
                }
                if (normal == true) {
                    System.out.println("Client " + ClientName + " message: " + msg);
                    out.println("You just said: " + msg);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PrintWriter getOut() {
        return out;
    }

    public String getClientName() {
        return ClientName;
    }

}
