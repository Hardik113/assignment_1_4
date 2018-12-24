package assignment.two;

import java.util.Comparator;

public class AgeDscComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		
       return o2.getAge() > o1.getAge() ? 1 : -1;
	}
	
	

}
