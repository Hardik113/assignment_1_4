package assignment.four;


public class ProcessingThread implements Runnable {
	
	private RawCollection rawCollection;
	private ProcessedCollection processedCollection;
	
	public ProcessingThread(RawCollection rawCollection, ProcessedCollection processedCollection) {
		this.rawCollection = rawCollection;
		this.processedCollection = processedCollection;
	}

	@Override
	public void run() {
		
		while(true) {
		
			try {
					Item item = rawCollection.get();
				
					item.computeTax();
				
					processedCollection.add(item);
				
		    	} catch (Exception e) {
		    		e.printStackTrace();
		    	}
		}
	}

}

