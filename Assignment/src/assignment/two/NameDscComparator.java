package assignment.two;

import java.util.Comparator;

public class NameDscComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		
       return o2.getName().compareTo(o1.getName());
       
	}
	
	

}
