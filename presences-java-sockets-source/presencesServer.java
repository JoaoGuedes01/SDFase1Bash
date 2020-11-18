import java.net.*;
import java.io.*;

public class presencesServer {
	static int DEFAULT_PORT = 2000;

	public static void main(String[] args) throws IOException {
		int port = DEFAULT_PORT;
		Presences presences = new Presences();

		ServerSocket servidor = null;
		ServerSocket welcomeSocket = new ServerSocket(9876);

		// Create a server socket, bound to the specified port: API
		// java.net.ServerSocket

		System.out.println("");
		System.out.println("***************************** Server listening on port: " + port + " *****************************");
		System.out.println("");

		while (true) {
			try {
				// Listen for a connection to be made to the socket and accepts it: API
				// java.net.ServerSocket
				Socket socketPedido = welcomeSocket.accept();

				// Start a GetPresencesRequestHandler thread
				GetPresencesRequestHandler thread = new GetPresencesRequestHandler(socketPedido, presences);
				thread.start();
			} catch (IOException e) {
				System.out.println("Erro na execucao do servidor: " + e);
				System.exit(1);
			}
		}
	}
}
