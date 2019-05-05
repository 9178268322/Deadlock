package com.ip;
/*
 * if we remove synchronized keyword then we will get data inconsitent problem
 */
public class Test{

    public static void main(String[] args) {
		
          Account ac = new Account();
          
          new Thread1(ac).start();
          new Thread2(ac).start();
          
	}
}

class Thread1 extends Thread {
	
	Account ac;
	
	public Thread1(Account ac) {
		this.ac = ac;
	}
    public void run() {
		
		try {
			ac.withdraw(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread2 extends Thread {
	
	Account ac;
	public Thread2(Account ac) {
		this.ac = ac;
	}
	public void run() {
		
		try {
			ac.withdraw(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Account {
	
	private double balance = 10000;
	
	public synchronized void withdraw(double amt) throws InterruptedException {
		
		System.out.println("In "+Thread.currentThread().getName()+" Before withdraw balance is: "+balance);
		
		this.balance = balance-amt;
		Thread.sleep(6000);
		System.out.println("In "+Thread.currentThread().getName()+" After withdrawn balance is:  "+balance);
	}
}



