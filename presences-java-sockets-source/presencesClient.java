import java.util.*;
import java.net.*;
import java.io.*;

public class presencesClient {

	static final int DEFAULT_PORT = 2000;
	static final String DEFAULT_HOST = "127.0.0.1";

	public static void main(String[] args) throws IOException {
		String servidor = DEFAULT_HOST;
		int porto = DEFAULT_PORT;

		if (args.length != 1) {
			System.out.println("Erro: use java presencesClient <ip>");
			System.exit(-1);
		}

		// Create a representation of the IP address of the Server: API
		// java.net.InetAddress

		InetAddress serverAddress = InetAddress.getByName("localhost");

		Socket ligacao = null;

		// Create a client sockets (also called just "sockets"). A socket is an endpoint
		// for communication between two machines: API java.net.Socket

		Socket socket = new Socket(serverAddress, 9876);
		// Parágrafo
		System.out.println("");
		System.out.println("");
		System.out.println("-----------------------Connected to : " + serverAddress + "-----------------------");
		// Parágrafo
		System.out.println("");
		try {

			// Create a java.io.BufferedReader for the Socket; Use
			// java.io.Socket.getInputStream() to obtain the Socket input stream
			// Pedidos Recebidos
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(in);

			// Create a java.io. PrintWriter for the Socket; Use
			// java.io.Socket.etOutputStream() to obtain the Socket output stream
			// Pedidos Enviados
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			String request = "get" + " " + args[0];
			// write the request into the Socket
			out.println(request);

			// Read the server response - read the data until null
			String resposta = br.readLine();
			System.out.println("Server Response status : " + resposta.replace("	Pedido recebido com Sucesso,", "").replace(","+args[0].toUpperCase(),"").replace(":","-"));
			System.out.println("");
			System.out.println("Server Response : " + resposta.replace("	Pedido recebido com Sucesso,status:200,",""));
			// Parágrafo
			System.out.println("");

			// Close the Socket
			socket.close();
			System.out.println("*** Terminou a ligacao! ***");
			System.out.println("");
			System.out.println("---------------------------------------------------------------------------------");

		} catch (IOException e) {
			System.out.println("Erro ao comunicar com o servidor: " + e);
			System.exit(1);
		}

	}
}
