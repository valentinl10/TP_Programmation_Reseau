package TP1_ex3;

public class Medecin extends Thread{

	SalleAttente salleAttente;
	String etatN, etatNMoins1;
	public Medecin(SalleAttente salleAttente) {
		this.salleAttente = salleAttente;
		etatN = "dort";
		etatNMoins1 = "null";
	}
	
	public void run(){
		while(true){
			if(!etatN.equals(etatNMoins1)) {
				System.out.println("Le medecin " + etatN);
				if(salleAttente.chaiseMedecinEstLibre) {
					etatNMoins1 = etatN;
					etatN = "est en consultation";
				}if(!salleAttente.chaiseMedecinEstLibre) {
					etatNMoins1 = etatN;
					etatN = "dort";
				}
					
			}
		}
	}
	
	public void Consultation(){
		System.out.println("Le medecin consulte.");
	}
	
	public void Assis(){
		System.out.println("Le medecin s'assoit et dort.");
	}


}
