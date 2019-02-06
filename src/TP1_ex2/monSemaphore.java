package TP1_ex2;

import java.util.concurrent.*;

public class monSemaphore extends Semaphore{

	private int permis;
	
	public monSemaphore(int initialPermis) {
		super(initialPermis);
		permis = initialPermis;
	}
	
	public synchronized void P() {
		permis = permis - 1;
		while (permis < 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("P, permis = " + permis);
	}
	
	public synchronized void V() {
		permis = permis + 1;
		if (permis <= 0) {
			notify();
		}
		//System.out.println("V, permis = " + permis);
	}

}
