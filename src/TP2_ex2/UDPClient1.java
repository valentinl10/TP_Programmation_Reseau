package TP2_ex2;

import java.io.BufferedReader;
import java.net.*;

public class UDPClient1 {
	public static void main(String[] args) {
		int i = 0;
		DatagramSocket s;
		while(i++ < 20) {
			try {
				s = new DatagramSocket();
				byte[] data = args[0].getBytes();
				aff(data);
				InetSocketAddress sa = new InetSocketAddress("localhost", 5678);
				DatagramPacket paquet = new DatagramPacket(data, data.length, sa);
				s.send(paquet);
				Thread.sleep(5000);
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
		}
	}
	static void aff(byte[] data) {
		System.out.println("Message à envoyer :");
		for(int i = 0; i < data.length; i++)
			System.out.print((char)data[i]);
		System.out.println("");
	}
}
