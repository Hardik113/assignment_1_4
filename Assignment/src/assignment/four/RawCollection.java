package assignment.four;

import java.util.ArrayList;
import java.util.List;

public class RawCollection {
	
	private List<Item> list;
	private int capacity;
	private int size;
	
	public RawCollection() {
		list = new ArrayList<>();
		capacity = 100;
		size = 0;
	}
	
	public void add(String name, String type, float price, int qty) throws Exception {
		

		synchronized (this) 
            { 
            	while (list.size() == capacity) {
                    wait(); 
            	}

                // to insert the jobs in the list 
            	if (type.equalsIgnoreCase("raw")) {
        			list.add(new RawItem(name, price, qty));
        		} else if (type.equalsIgnoreCase("imported")) {
        			list.add(new ImportedItem(name, price, qty));
        		} else {
        			list.add(new ManufacturedItem(name, price, qty));
        		}

            	size++;
            	
                // notifies the consumer thread that 
                // now it can start consuming 
            	
                notifyAll(); 

          } 
		
	}
	
	public Item get() throws Exception {
		
        synchronized (this) 
            { 
                // consumer thread waits while list 
                // is empty 
                while (list.size() == 0) {
                    wait(); 
                }
                
                
                Item val = list.remove(size-1);
                
                size -= 1;
                
                // Wake up producer thread 
                notifyAll(); 
                
                return val;

            }
	}
	
	public synchronized boolean isAvailable() {
		return size > 0;
	}

}
