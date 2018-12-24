package assignment.four;

import java.util.ArrayList;
import java.util.List;

public class ProcessedCollection {
	
	private List<Item> list;
	private int capacity;
	private int size;
	
	public ProcessedCollection() {
		list = new ArrayList<>();
		capacity = 100;
		size = 0;
	}
	
	public void add(Item item) throws Exception {
		

		synchronized (this) 
            { 

                // to insert the jobs in the list 
            	item.computeTax();
            	
            	list.add(item);

            	size++;
                // notifies the consumer thread that 
                // now it can start consuming 
          } 
		
	}
	
//	public Item get() throws Exception {
//		
//        synchronized (this) 
//            { 
//                // consumer thread waits while list 
//                // is empty 
//                while (list.size() == 0) 
//                    wait(); 
//
//                Item val = list.remove(--size);
//                
//                // Wake up producer thread 
//                notify(); 
//                
//                return val;
//
//            }
//	}
	
	public void display() {
		
		for(Item item: list) {
			System.out.println(item);
		}
	}
	
	
	
	

}
