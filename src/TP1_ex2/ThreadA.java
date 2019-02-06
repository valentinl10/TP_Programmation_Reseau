package TP1_ex2;

public class ThreadA extends Thread  {

	String nom = "A";
	monSemaphore monsemaphore;
	
	public ThreadA(monSemaphore monsemaphore) {
		this.monsemaphore = monsemaphore;
	}
	
	public void run () {
		monsemaphore.P();
		System.out.println("Traitement de " + nom);

		monsemaphore.V();
	}
}
