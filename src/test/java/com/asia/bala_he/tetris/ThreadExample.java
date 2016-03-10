package com.asia.bala_he.tetris;

public class ThreadExample {

	public static void main(String[] args) {
		Runnable r = new Runnable() {
			
			public void run() {
				for(int i=0;i<20;i++){
					System.out.println("hello "+Thread.currentThread().getName());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Thread t = new Thread(r,"Toto");
		t.start();
		new Thread(r,"titi").start();
	}
	
}
