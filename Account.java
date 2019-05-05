package Essential_Program;
/*
 *  if threads are calling synchronized method on same object only one thread can allowed mean while 
 *  other threads will wait.
 *  But other threads can execute other  methods on same object
 *  if i remove comment in line 43 and 69 it will execute
 *  
 *   if you don't want deadlock in this program just remove synchronized keyword from any one method
 */
public class Account extends Thread{

	 Husband h = new Husband();
	 Wife w = new Wife();

	 public void transaction() {
	        
			 this.start();
			 h.checkingBalance(w);
	 }

    public void run() {
	 
	      w.checkingBalance(h);
	 }
	public static void main(String[] args) {
		
		Account ac = new Account();
		 ac.transaction();
	}

}

class Husband {

    public synchronized void checkingBalance(Wife w) {
	
			System.out.println("Wife checking balance");

			try {
			     Thread.sleep(6000);	
			}
			catch (InterruptedException ei) { }
			
			       System.out.println("Wife trying to withdrawal");
				   //w.m1();
			       w.withdraw();
				   
	}
    public synchronized void withdraw() {
			
			       System.out.println("Money withdrawan by husband");
	}

	public void m1() {
	
	      System.out.println("Hello wife");
	}
}

class Wife {

     public synchronized void checkingBalance(Husband h) {
	
			System.out.println("Husband checking balance");

			try {
			     Thread.sleep(6000);	
			}
			catch (InterruptedException ei){}
			       System.out.println("Husband trying to withdrawal");
			       //h.m1();
				   h.withdraw();
				   
     }
			public synchronized void withdraw() {
			
			       System.out.println("Money withdrawan by wife");
			}

			public void m1() {
			
			     System.out.println("Hello Husband");
			}
}
