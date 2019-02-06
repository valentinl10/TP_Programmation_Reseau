package TP1_ex1;

import java.util.Date;

public class Voiture extends Thread{
	
	vPark vpark;
	long attente = 20000;
	public String nom;

	public Voiture(vPark vpark, int i) {
		this.vpark = vpark;
		nom = "Voiture" + i;
	}

	public void run() {
		vpark.Arriver();
		System.out.println(nom + " est arrivée " + new Date());
		try {
			sleep(attente);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(nom + " est partie " + new Date());
		vpark.Partir();
	}
	
}
