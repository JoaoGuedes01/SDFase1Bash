import java.net.*;
import java.io.*;

public class presencesServer {
	static int DEFAULT_PORT = 5500;

	public static void main(String[] args) throws IOException {
		int port = DEFAULT_PORT;
		Presences presences = new Presences();

		// Create a server socket, bound to the specified port: API
		// java.net.ServerSocket
		ServerSocket server = new ServerSocket(port);

		System.out.println("Servidor a' espera de ligacoes no porto " + port);

		while (true) {
			try {
				// Listen for a connection to be made to the socket and accepts it: API
				// java.net.ServerSocket
				Socket ligacao = server.accept();

				// Start a GetPresencesRequestHandler thread + comando de iniciar a thread
				GetPresencesRequestHandler thread = new GetPresencesRequestHandler(ligacao, presences);
				thread.start();

			} catch (IOException e) {
				System.out.println("Erro na execucao do servidor: " + e);
				System.exit(1);
			}
		}
	}
}
