import java.net.*;
import java.io.*;
import java.util.*;

public class GetPresencesRequestHandler extends Thread {
	Socket ligacao;
	Presences presences;
	BufferedReader in;
	PrintWriter out;

	public GetPresencesRequestHandler(Socket ligacao, Presences presences) {
		this.ligacao = ligacao;
		this.presences = presences;
		try {
			this.in = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));
			this.out = new PrintWriter(ligacao.getOutputStream());
		} catch (IOException e) {
			System.out.println("Erro na execucao do servidor: " + e);
			System.exit(1);
		}
	}

	public void run() {
		try {
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("");
			System.out.println("	Aceitou ligacao de cliente no endereco " + ligacao.getInetAddress() + " na porta "
					+ ligacao.getPort());
			System.out.println("");
			String response, ReqNmbr, allReqs;
			String msg = in.readLine();
			System.out.println("	Client Request : " + msg);
			System.out.println("");
			System.out.println("	Client Message : " + msg.replace("get","").trim());
			System.out.println("");
			StringTokenizer tokens = new StringTokenizer(msg);
			String metodo = tokens.nextToken();
			if (metodo.equals("get")) {
				response = "	Pedido recebido com Sucesso,status:200,";
				ReqNmbr = "	Nmr Requests Diferentes Recebidos : ";
				allReqs = "	Requests Recebidos : ";
				String ip = tokens.nextToken();
				Vector<String> ipList = presences.getPresences(ip);
				System.out.println("");
				ReqNmbr += ipList.size();
				for (Iterator<String> it = ipList.iterator(); it.hasNext();) {
					String next = it.next();
					allReqs += next + ";";
				}
				
				response += msg.replace("get","").trim().toUpperCase(); 
				System.out.println(ReqNmbr);
				System.out.println("");
				System.out.println(allReqs);
				System.out.println("");
				System.out.println("	Connection closed...");
				System.out.println("");
				System.out.println("-------------------------------------------------------------------------------------------");
				out.println(response);
				
			} else
				out.println("201;method not found");

			out.flush();
			in.close();
			out.close();
			ligacao.close();
		} catch (IOException e) {
			System.out.println("Erro na execucao do servidor: " + e);
			System.exit(1);
		}
	}
}