package TP4_ex1;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class XMLServer {

	public static void main(String[] args) {
		try {
			System.out.println("---Server---");
			Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			XMLOperations operations = new XMLOperations();
			Naming.bind("rmi://localhost/od", operations);
			System.out.println("blind : " + operations);
		} catch (Exception e) {
			System.out.println("ERREUR");
			e.printStackTrace();
		}
	}

}
