package TP3_ex1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientDir {
	// Répertoires des fichiers à télécharger et à charger
	// PATH_DIR_DOWN et PATH_DIR_UP à changer
	public static final String PATH_DIR_DOWN = "C:\\Users\\valen\\Downloads\\";
	public static final String PATH_DIR_UP = "C:\\Users\\valen\\Downloads\\";

	public static final int PORT = 1111;
	
	public static void main(String[] args) throws IOException {
		if(args.length < 1)
			return;
		String commande = args[0];
		String nomFichier = null;
		if(args.length > 1)
			nomFichier = args[1];
		System.out.println("commande : " + commande);
		System.out.println("nomFichier : " + nomFichier);

		try {
			System.out.println("Je vais essayer de me connecter...");
			Socket service = new Socket("localhost", PORT);
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(service.getOutputStream()));
			BufferedReader bf = new BufferedReader(new InputStreamReader(service.getInputStream()));

			int num, t = 1024;
			byte[] octets = new byte[t];
			
			if(commande.equals("LS")) { //--> COMMANDE LS <--
				pw.println("LS_DIR");
				pw.flush();
				String line;
				String listeFichiers = "";
				while((line = bf.readLine()) != null) {
					listeFichiers += line + "\n";
				}
				System.out.println("listeFichiers : " + listeFichiers);
				
			}
			if(commande.equals("GET")) { //--> COMMANDE GET <--
				if(args.length < 2) {
					System.out.println("La commande GET nécessite un nom de fichier");
				}
				else {
					pw.println("GET_FILE " + nomFichier);
					pw.flush();
					String line;
					String contenuFichier = "";
					while((line = bf.readLine()) != null) {
						contenuFichier += line + "\n";
					}
					System.out.println("contenuFichier : " + contenuFichier);
					PrintWriter pw2 = new PrintWriter(new File(PATH_DIR_DOWN + nomFichier));
					pw2.write(contenuFichier);
					pw2.close();
				}
				
			}
			if(commande.equals("PUT")) { //--> COMMANDE PUT <--
				if(args.length < 2) {
					System.out.println("La commande PUT nécessite un nom de fichier");
				}
				else {
					pw.println("PUT_FILE " + nomFichier);
					pw.flush();
					BufferedInputStream bfi = new BufferedInputStream(new FileInputStream(PATH_DIR_UP + nomFichier));
					BufferedOutputStream bfo = new BufferedOutputStream(service.getOutputStream());
					while((num = bfi.read(octets, 0, t)) != -1)
						bfo.write(octets, 0, num);
					bfo.flush();
					bfo.close();
					bfi.close();
				}
				
			}
			pw.close();
			bf.close();
			service.close();
		} catch (Exception e) {
			System.err.println("Erreur sérieuse : " + e);
			e.printStackTrace();
			System.exit(1);
		}
	}
}