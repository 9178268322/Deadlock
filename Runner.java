package com.ip;

class Factory {
	
	int items;
	boolean itemsInFactory;
	
	public synchronized void produce(int items) {
		
		if(itemsInFactory) {
			
			    try {
						wait();
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
		}
		System.out.println("Produced item: "+items);
		this.items = items;
		itemsInFactory = true;
		notify();
	}
	
	public synchronized void consume() {
		
		if(!itemsInFactory) {
			
			 try {
					wait();
			} catch (InterruptedException e) {
					e.printStackTrace();
			}
		}
		System.out.println("Consumed item: "+items);
		itemsInFactory = false;
	    notify();	
	}
}

class Producer implements Runnable {
	
	Factory fa;
	public Producer(Factory fa) {
		this.fa = fa;
		Thread t = new Thread(this,"Producer");
		t.start();
	}
	
	public void run() {
		
		int i = 1;
		while(i<=10) {
			
			fa.produce(i++);
			
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
			
		}
	}
}

class Consumer implements Runnable {
	
	Factory fa;
	public Consumer(Factory fa) {
		
		this.fa = fa;
		Thread t = new Thread(this,"Consumer");
		t.start();
	}
	
	public void run() {
		
		int i = 1;
		while(i<=10) {
			
			fa.consume();
			try {Thread.sleep(1000);} catch (InterruptedException e) {}
		}
	}
}
public class Runner {
	
	public static void main(String[] args) {
		
		Factory fa = new Factory();
		
		Producer p = new Producer(fa);
		Consumer c = new Consumer(fa);
	}
}
