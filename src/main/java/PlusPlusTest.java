
public class PlusPlusTest {
	public static void main(String[] args) throws InterruptedException {
 		Num num = new Num();
		ThreadA threadA = new ThreadA(num);
		ThreadA threadB = new ThreadA(num);
		threadA.start();
		
		threadA.join();
		
		threadB.start();
	 	Thread.sleep(10000);
	  
		System.out.println(num.count); 
	
		 
	}
}

 
class ThreadA extends Thread {
	private static  Num num;

	public ThreadA(Num num) {
		this.num = num;
	}

	//synchronized 只针对本对象进行锁，而多线程的时候，必须对同一对象进行上锁
	public   synchronized void  numAdd(){
		for (int i = 0; i < 10000; i++) {
			num.count++;
		}
		
	}
	
/*	public    void  numAdd(){
		synchronized(num){
			for (int i = 0; i < 10000; i++) {
				num.count++;
			}
			
		}

	}	*/
	
	@Override
	public void run() {
		numAdd(); 
	}
}

class ThreadB extends Thread {
	private  static Num num;

	public ThreadB(Num num) {
		this.num = num;
	}

	public static synchronized void  numAdd(){
		for (int i = 0; i < 10000; i++) {
			num.count = num.count +1 ; 
		}
	}	
	@Override
	public void run() {
		numAdd();
	}
}

class Num {
	int count = 0;

	public Num() {
	}
}