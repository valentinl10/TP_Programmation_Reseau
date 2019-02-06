package TP1_ex2;

public class ThreadC extends Thread {

	String nom = "C";
	monSemaphore monsemaphore;
	
	public ThreadC(monSemaphore monsemaphore) {
		this.monsemaphore = monsemaphore;		
	}
	
	public void run () {
		monsemaphore.P();
		System.out.println("Traitement de " + nom);

		monsemaphore.V();
	}
}
