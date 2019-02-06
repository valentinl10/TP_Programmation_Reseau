package TP1_ex3;

import java.util.Random;

public class Clinique extends Thread{

	private int nChaises;
	SalleAttente salleAttente;
	
	public Clinique(int nChaises) {
		this.nChaises = nChaises;
		salleAttente = new SalleAttente(nChaises);
	}
	
	public void run(){
		Medecin doc = new Medecin(salleAttente);
		doc.start();
		int i = 0;
		while(true){
			i++;
			Patient patient = new Patient(i, salleAttente);
			patient.start();
			try {
				sleep((long) (Math.random() * 2000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
