package controller;

import java.util.concurrent.Semaphore;

public class ThreadProcess extends Thread{

	private int id;
	private Semaphore semaforo;
	private long calcTime;
	private long transactionTime;
	
	public ThreadProcess(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		
		calc();
		try {
			semaforo.acquire();
			transaction();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
	}

	private void calc() {
		if(id%3==0) {
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println("Thread #" + id + " calculando...");
					calcTime = (int) (Math.random() * 1001) + 1000;
					sleep(calcTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else if(id%3==1) {
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println("Thread #" + id + " calculando...");
					calcTime = (int) (Math.random() * 801) + 200;
					sleep(calcTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else {
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println("Thread #" + id + " calculando...");
					calcTime = (int) (Math.random() * 1001) + 500;
					sleep(calcTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void transaction() {
		if(id%3==1) {
			transactionTime = 1000;
			for (int i = 0; i < 2; i++) {
				try {
					System.out.println("Thread #"+id+" requisitando o BD...");
					sleep(transactionTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else {
			transactionTime = 1500;
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println("Thread #"+id+" requisitando o BD...");
					sleep(transactionTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
