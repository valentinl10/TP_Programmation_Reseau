package TP1_ex1;

public class vPark{
	
	int capaciteMax;
	int capacite;

	public vPark() {
		capaciteMax = 4;
		capacite = capaciteMax;
		System.out.println("Parking créé.");
	}
	
	public synchronized void Arriver() {
		while (capacite <= 0 || capacite > capaciteMax) {
			try {
				wait(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		capacite--;
		System.out.println("Capacite " + capacite);
	}
	
	public synchronized void Partir() {
		capacite++;
		System.out.println("Capacite " + capacite);
		notify();
		
	}
}
