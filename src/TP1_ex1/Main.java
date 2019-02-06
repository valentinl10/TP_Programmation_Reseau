package TP1_ex1;


public class Main{

	public static void main(String[] args) throws InterruptedException {
		// Main à exécuter pour faire fonctionner le parking
		
		vPark vpark = new vPark();
		
		Voiture voiture;
		for(int i = 0; i < 100; i++) {
			voiture = new Voiture(vpark, i);
			voiture.start();
			Thread.sleep((long)(Math.random()*5000));
		}

		
	}

}
