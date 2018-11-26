import java.util.HashMap;

public class Test3 {

	public static void main(String[] args) {
		/*People p1 = new People("Jack", 12);
        System.out.println(p1.hashCode());
             
        HashMap<People, Integer> hashMap = new HashMap<People, Integer>();
        hashMap.put(p1, 1);
         
        System.out.println(hashMap.get(new People("Jack", 12)));*/
		
		 
			
			Thread t1 = new Thread(new LockTest() ) ;
			
			Thread t2 = new Thread(new LockTest() ) ;
			
			Thread t3 = new Thread(new LockTest() ) ;
			
		 
			HashMap map ; 
		
	 
	}

}
class LockTest  implements Runnable{
	
	public static int i = 0 ; 
	
    public synchronized void lockA() {
       i ++ ; 
    }
    public synchronized void lockB() {
         
    }
	@Override
	public void run() {
		 
		lockA() ;  
		
		System.out.println(i);
	}
}