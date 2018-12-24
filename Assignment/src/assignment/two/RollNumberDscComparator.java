package assignment.two;

import java.util.Comparator;

public class RollNumberDscComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		
       return o2.getRollNumber() > o1.getRollNumber() ? 1 : -1;
       
	}
	
	

}
