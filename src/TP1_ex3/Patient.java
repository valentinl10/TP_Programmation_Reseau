package TP1_ex3;

import java.util.Date;

public class Patient extends Thread {

	int id;
	SalleAttente salleAttente;
	boolean estExaminé;
	boolean estAssis;
	
	public Patient(int id, SalleAttente salleAttente) {
		this.id = id;
		this.salleAttente = salleAttente;
		estExaminé = false;
		estAssis = false;
	}
	
	public void run(){
		if(salleAttente.nChaisesLibres > 0) {
			salleAttente.Arriver();
			System.out.println("Le patient " + id + " est arrivé " + new Date());
			System.out.println("nChaisesLibres : " + salleAttente.nChaisesLibres);
			if(!salleAttente.chaiseMedecinEstLibre) {
				Assis();
			}
			while(!salleAttente.chaiseMedecinEstLibre);
			if(estAssis) {
				Leve();
			}
				try {
				salleAttente.chaiseMedecinEstLibre = false;
				DébutExamen();
				sleep((long)Math.random()*5000);
				FinExamen();
				salleAttente.chaiseMedecinEstLibre = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			salleAttente.Partir();
			System.out.println("Le patient " + id + " est parti " + new Date());
			System.out.println("nChaisesLibres : " + salleAttente.nChaisesLibres);
		} 
		else {
			ManquePlace();
		}
	
	}
	
	public void DébutExamen(){
		System.out.println("Le patient " + id + " se fait examiné.");
	}
	
	public void FinExamen(){
		System.out.println("Le patient " + id + " s'est fait examiné.");
	}
	
	public void ReveillerMedecin(){
		System.out.println("Le patient " + id + " réveille le medecin.");
	}
	
	public void Assis(){
		System.out.println("Le patient " + id + " s'assoit et attend.");
		System.out.println("nChaisesLibres : " + salleAttente.nChaisesLibres);
		estAssis = true;
		salleAttente.nChaisesLibres--;
	}
	
	public void Leve(){
		System.out.println("Le patient " + id + " se lève et va en consultation.");
		System.out.println("nChaisesLibres : " + salleAttente.nChaisesLibres);
		estAssis = false;
		salleAttente.nChaisesLibres++;
	}
	
	public void Partir(){
		System.out.println("Le patient " + id + " est parti.");
	}
	
	public void ManquePlace(){
		System.out.println("Le patient " + id + " est parti par manque de place.");
	}

}
