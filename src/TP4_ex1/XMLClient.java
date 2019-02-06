package TP4_ex1;

import java.rmi.Naming;
import java.util.ArrayList;

public class XMLClient {

	public static void main(String[] args) {
		if(args.length < 2)
			return;
		try {
			XMLOperationsI operationsI = (XMLOperationsI)Naming.lookup("//localhost/od");
			System.out.println("Naming : " + operationsI);
			
			System.out.println("---CLIENT---");
			String nom = args[0];
			String port = args[1];
			String commande, pseudo, mdp;
			//XMLOperations operation = new XMLOperations();
			System.out.println("nom : " + nom);
			System.out.println("port : " + port);
			for(int i = 2; i < args.length;) {
				commande = args[i++];
				//System.out.println("commande : " + commande);
				if(commande.contains("P_EXIST")) {
					System.out.println("nom commande : " + "P_EXIST");
					pseudo = args[i].substring(args[i].indexOf("<") + 1, args[i].indexOf(">"));
					i++;
					System.out.println("pseudo : " + pseudo);
					if(operationsI.pseudoExist(pseudo))
						System.out.println("Le pseudo " + pseudo + " est déjà utilisé");
					else
						System.out.println("Le pseudo " + pseudo + " n'est pas utilisé");
				}
				if(commande.contains("U_EXIST")) {
					System.out.println("nom commande : " + "U_EXIST");
					pseudo = args[i].substring(args[i].indexOf("<") + 1, args[i].indexOf(">"));
					i++;
					mdp = args[i].substring(args[i].indexOf("<") + 1, args[i].indexOf(">"));
					i++;
					System.out.println("pseudo : " + pseudo);
					System.out.println("mdp : " + mdp);
					if(operationsI.userExist(pseudo, mdp))
						System.out.println("L'accès est refusé, le pseudo et/ou le mot de passe sont incorrects");
					else
						System.out.println("Le pseudo et le mot de passe sont corrects");						
				}
				if(commande.contains("ADD")) {
					System.out.println("nom commande : " + "ADD");
					pseudo = args[i].substring(args[i].indexOf("<") + 1, args[i].indexOf(">"));
					i++;
					mdp = args[i].substring(args[i].indexOf("<") + 1, args[i].indexOf(">"));
					i++;
					System.out.println("pseudo : " + pseudo);
					System.out.println("mdp : " + mdp);
					if(operationsI.addUser(pseudo, mdp))
						System.out.println("Le client " + pseudo + " a bien été inséré");
					else
						System.out.println("Le client n'a pas pu être inséré");
				}
				if(commande.contains("REMOVE")) {
					System.out.println("nom commande : " + "REMOVE");
					pseudo = args[i].substring(args[i].indexOf("<") + 1, args[i].indexOf(">"));
					i++;
					System.out.println("pseudo : " + pseudo);
					if(operationsI.removeUser(pseudo))
						System.out.println("Le client est bien supprimé");
					else
						System.out.println("Le client n'a pas pu être supprimé");
				}
			}
		} catch (Exception e) {
			System.out.println("ERREUR");
			e.printStackTrace();
		}

	}

}
