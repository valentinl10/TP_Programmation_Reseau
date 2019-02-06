package TP2_ex1;

import java.net.*;

public class UDPSeveur {
	public static void main(String[] args) {
		try {
			int somme = 0;
			boolean b;
			DatagramSocket s = new DatagramSocket(5678);
			byte[] data = new byte[100];
			DatagramPacket paquet = new DatagramPacket(data, data.length);
			String st;
			do {
				System.out.println("Attente de réception du paquet.");
				s.receive(paquet);
				st = new String(paquet.getData(), 0, paquet.getLength());
				System.out.print("J'ai reçu [" + st + "]");
				System.out.println(" depuis la machine " + paquet.getAddress().getCanonicalHostName() + " via le port " + paquet.getPort());
				b = testInt(st) != -1;
				if(!b)
					System.out.println("Ceci n'est pas un entier");
				else {
					somme += testInt(st);
					System.out.println("La somme est de " + somme);
				}
			} while (b);
			System.out.println("La somme finale est de " + somme);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
	
	// Fonction permettant de vérifier si on a un entier en entrée
	// Fonction qui revoit l'entier depuis le string
	static int testInt(String st) {
		boolean b = true;
		int s = 0;
		for(int i = 0; i < st.length(); i++) {
			if(st.charAt(i) < 58 && st.charAt(i) > 47)
				s += Math.pow(10, st.length() - 1 - i) * (st.charAt(i) - 48);
			else
				b = false;
		}
		if(b)
			return s;
		else
			return -1;
	}

}
