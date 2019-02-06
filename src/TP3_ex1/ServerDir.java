package TP3_ex1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDir {
	
	public static final int PORT = 1111;
	// Répertoire DIR
	// PATH_DIR à changer
	public static final String PATH_DIR = "C:\\Users\\valen\\Desktop\\TP_Programmation_Reseau\\src\\TP3_ex1\\";

	public static void main(String[] args) {
		
		try {
			ServerSocket socketAttente = new ServerSocket(PORT);
			do {
				System.out.println("Attente de connexions, serveur prêt");
				Socket service = socketAttente.accept();
				BufferedReader bf = new BufferedReader(new InputStreamReader(service.getInputStream()));
				String message = bf.readLine();
				//bf.close();
				System.out.println("message : " + message);
				
				String commande, nomFichier = null;
				int num, t = 1024;
				byte[] octets = new byte[t];
				
				if(message.equals("LS_DIR")) { //--> COMMANDE LS_DIR <--
					commande = "LS_DIR";
					System.out.println("commande : " + commande);
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(service.getOutputStream()));
					String [] listeFichiers = new File(PATH_DIR).list();
					int i;
					for (i = 0; i < listeFichiers.length; i++)
						pw.println(listeFichiers[i]);
					pw.close();
				}
				if(message.contains(" ")) {
					if(message.substring(0, message.indexOf(" ")).equals("GET_FILE")) { //--> COMMANDE GET_FILE <--
						commande = "GET_FILE";
						nomFichier = message.substring(message.indexOf(" ") + 1);
						System.out.println("commande : " + commande);
						System.out.println("nomFichier : " + nomFichier);
						
						BufferedInputStream bfi = new BufferedInputStream(new FileInputStream(PATH_DIR + nomFichier));
						BufferedOutputStream bfo = new BufferedOutputStream(service.getOutputStream());
						while((num = bfi.read(octets, 0, t)) != -1)
							bfo.write(octets, 0, num);
						bfo.flush();
						bfo.close();
						bfi.close();
					}
					if(message.substring(0, message.indexOf(" ")).equals("PUT_FILE")) { //--> COMMANDE PUT_FILE <--
						commande = "PUT_FILE";
						nomFichier = message.substring(message.indexOf(" ") + 1);
						System.out.println("commande : " + commande);
						System.out.println("nomFichier : " + nomFichier);

						String line;
						String contenuFichier = "";
						while((line = bf.readLine()) != null) {
							contenuFichier += line + "\n";
						}
						System.out.println("contenuFichier : " + contenuFichier);
						PrintWriter pw2 = new PrintWriter(new File(PATH_DIR + nomFichier));
						pw2.write(contenuFichier);
						pw2.close();
					}
				}
				
				Thread.sleep(7000);
				//PrintWriter pw = new PrintWriter(new OutputStreamWriter(service.getOutputStream()));
				//reponse = message;
				//pw.println("j'ai bien reçu ton message " + reponse);
				//pw.close();
				service.close();
			} while (true);
		} catch (Exception e) {
			System.err.println("Erreur : " + e);
			e.printStackTrace();
			System.exit(1);
		}

	}
	
	
}
