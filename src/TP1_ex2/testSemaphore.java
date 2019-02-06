package TP1_ex2;

public class testSemaphore {

	static final int N = 5;
	static Thread thread;  
	static monSemaphore sem = new monSemaphore(1);
	
	public static void main(String[] args) {
		System.out.println("Test : ");
		for(int i = 0; i < N; i++){
			//System.out.println("Boucle " + i);
			thread = new ThreadA(sem);
			thread.run();
			thread = new ThreadB(sem);
			thread.run();
			thread = new ThreadC(sem);
			thread.run();
		}
		System.out.println("Fin test.");
	}

}
