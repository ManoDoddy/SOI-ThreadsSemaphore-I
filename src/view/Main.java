package view;

import java.util.concurrent.Semaphore;

import controller.ThreadProcess;

public class Main {

	public static void main(String[] args) {
		
		int id = 0;
		Semaphore semaforo = new Semaphore(1);
		
		for (int i = 0; i < 21; i++) {
			Thread process = new ThreadProcess(++id, semaforo);
			process.start();
		}
		
	}

}
