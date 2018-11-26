
public class Test4 {

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			Thread thread = new MyThread();
			thread.start();
		}
	}

}
class Sync {
	public  void test() {
		synchronized(Sync.class){
			System.out.println("test开始..");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("test结束..");
		}

	}
}
class MyThread extends Thread {

	public void run() {
		Sync sync = new Sync();
		sync.test();
	}
}