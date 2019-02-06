package TP1_ex2;

public class ThreadB extends Thread  {

	String nom = "B";
	monSemaphore monsemaphore;
	
	public ThreadB(monSemaphore monsemaphore) {
		this.monsemaphore = monsemaphore;
	}
	
	public void run () {
		monsemaphore.P();
		System.out.println("Traitement de " + nom);

		monsemaphore.V();
	}
}
