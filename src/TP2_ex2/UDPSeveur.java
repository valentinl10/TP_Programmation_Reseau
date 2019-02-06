package TP2_ex2;

import java.net.*;
import java.util.ArrayList;

public class UDPSeveur {
	public static void main(String[] args) {
		ArrayList<addClient> list = new ArrayList<addClient>();
		int nbClient = 0;
		try {
			boolean bInt, bAddr;;
			DatagramSocket s = new DatagramSocket(5678);
			byte[] data = new byte[100];
			DatagramPacket paquet = new DatagramPacket(data, data.length);
			String st;
			addClient client;
			int nb = 0;
			do {
				System.out.println("Attente de réception du paquet.");
				s.receive(paquet);
				st = new String(paquet.getData(), 0, paquet.getLength());
				System.out.print("J'ai reçu [" + st + "]");
				System.out.println(" depuis la machine " + paquet.getAddress().getHostAddress() + " via le port " + paquet.getPort());
				bAddr = addrExist(list, paquet.getAddress().getHostAddress()) != -1;
				bInt = testInt(st) != -1;
				if(!bInt)
					System.out.println("Ceci n'est pas un entier");
				else {
					if(!bAddr) {
						System.out.println("Nouveau client");
						client = new addClient(paquet.getAddress().getHostAddress(), paquet.getPort());
						client.somme += testInt(st);
						list.add(client);
						System.out.println("La somme est de " + client.somme);
						
					}
					else {
						client = list.get(addrExist(list, paquet.getAddress().getHostAddress()));
						client.somme += testInt(st);
						client.port++;
						System.out.println("La somme est de " + client.somme);
					}
				}
				nb++;
			} while (bInt && nb < 20);
			for(int i = 0; i < list.size(); i++)
				System.out.println("La somme finale de " + list.get(i).ip + ":" + list.get(i).port + " est de " + list.get(i).somme);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
	
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
	
	static int addrExist(ArrayList<addClient> list, String ip) {
		for(int i = 0; i < list.size(); i++)
			if(list.get(i).ip == ip)
				return i;
		return -1;
		
	}

}
