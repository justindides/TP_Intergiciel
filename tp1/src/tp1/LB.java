package tp1;


import java.lang.Thread;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;



/** Le load balancer */
public class LB extends Thread {
	
	static String hosts[] = {"localhost", "localhost"};
	static int ports[] = {8081,8082};
	static int nbHosts = 2;
	static Random rand = new Random();
	Socket sock;
	
	public LB(Socket s) {
		sock = s;
	}
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket ss = new ServerSocket(8080);

		while (true) { 
			LB threadLB = new LB(ss.accept());
			System.out.println("Client accepté");
			threadLB.start();
			ss.close();
		}
	}
	
	public void run( ) {
	
		/* Streams venant et allant vers le client */
		try {
			OutputStream inputOS = sock.getOutputStream();
	
			InputStream inputIS = sock.getInputStream();
			
			/* Choix aléatoire d'un serveur de traitement */
			int n = rand.nextInt(nbHosts);
			/* Création d'une socket vers ce serv */
			Socket dest = new Socket(hosts[n], ports[n]);
			
			/* Streams verseurs traitant */
			OutputStream outputOS = dest.getOutputStream();
			InputStream outputIS = dest.getInputStream();
			
			byte[] buf = new byte[1024];
			
			int nblu = inputIS.read(buf, 0, 1024);
			
			System.out.println("Lecture client : " + dest.getPort());
			
			outputOS.write(buf, 0, nblu);
			
			nblu = outputIS.read(buf, 0, 1024);
			inputOS.write(buf, 0, nblu);
			
			inputOS.close();
			inputIS.close();
			outputOS.close();
			outputIS.close();
			
			dest.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
