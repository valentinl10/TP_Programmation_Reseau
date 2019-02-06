package TP1_ex3;

public class SalleAttente{

	int nChaisesLibres, nChaises;
	int nPatients;
	boolean chaiseMedecinEstLibre;
	boolean chaisePatientEstLibre;
	
	public SalleAttente(int nChaises) {
		nPatients = 1;
		nChaisesLibres = nChaises;
		this.nChaises = nChaises;
		chaiseMedecinEstLibre = true;
		//chaisePatientEstLibre = true;
	}
	

	public synchronized void Arriver() {
		while (nChaisesLibres <= 0 || nChaisesLibres > nChaises) {
			try {
				wait(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void Partir() {
		notify();
		
	}

}
