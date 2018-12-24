package assignment.four;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("Starting....");
		
		RawCollection rawCollection = new RawCollection();
		ProcessedCollection processedCollection = new ProcessedCollection();
		
		Thread rawDataThread = new Thread(new DatabaseThread(rawCollection));
		Thread processedDataThread = new Thread(new ProcessingThread(rawCollection, processedCollection));
		
		
		
		rawDataThread.start();
		
		processedDataThread.start();
		
		try {
			rawDataThread.join();
			
			System.out.println("Database items exhausted");
			
			processedCollection.display();
			
			processedDataThread.join();
			
			
			System.out.println("All items processed");
		
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
