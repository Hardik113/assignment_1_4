package assignment.four;

public class ThreadTest {

	public static void main(String[] args) throws InterruptedException {
		
		Thread t = new Thread(new DatabaseThread(new RawCollection()));
		t.start();
		
		t.join();
		
		System.out.println("Everythings done");

	}

}
