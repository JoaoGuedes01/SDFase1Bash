import java.util.*;
import java.net.*;
import java.io.*;

public class presencesClient {

	static final int DEFAULT_PORT = 5500;
	static final String DEFAULT_HOST = "127.0.0.1";

	public static void main(String[] args) throws IOException{
		String servidor = DEFAULT_HOST;
		int port = DEFAULT_PORT;

		//Verificar se o utilizador passa um Ip ao iniciar o programa presencesClient
		if (args.length != 2) {
			System.out.println("Erro: use java presencesClient <ip> <localizacao>");
			System.exit(-1);
		}

		// Create a representation of the IP address of the Server: API
		// java.net.InetAddress
		InetAddress serverAddress = InetAddress.getByName("localhost");

		// Create a client sockets (also called just "sockets"). A socket is an endpoint
		// for communication between two machines: API java.net.Socket
		Socket socket = new Socket(serverAddress, port);

		try {
			// Create a java.io.BufferedReader for the Socket; Use
			// java.io.Socket.getInputStream() to obtain the Socket input stream
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// Create a java.io. PrintWriter for the Socket; Use
			// java.io.Socket.etOutputStream() to obtain the Socket output stream
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			String request = "get" + " " + args[0] + " " + args[1];
			System.out.println("request: " + request);

			// write the request into the Socket
			out.println(request);

			// Read the server response - read the data until null
			String msg = in.readLine();
			System.out.println(msg);

			// Close the Socket
			socket.close();

			System.out.println("Terminou a ligacao!");
			
		} catch (IOException e) {
			System.out.println("Erro ao comunicar com o servidor: " + e);
			System.exit(1);
		}

	}
}
