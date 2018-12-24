package assignment.two;

import java.util.Comparator;

public class AddressDscComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		
       return o2.getAddress().compareTo(o1.getAddress());
       
	}
	
	

}
