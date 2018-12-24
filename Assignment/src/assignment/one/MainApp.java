package assignment.one;

import java.util.*;

public class MainApp {
	
	static List<Item> itemList = new LinkedList<>();
	static Scanner sc = new Scanner(System.in);
	
	public static void getInput() {
		String name = "", price = "", quantity = "", input[], type = "";
		
		for(int i = 0; i < 4; i++) {
			input = sc.nextLine().split(" ");
	
			if (input[0].startsWith("-name")) {
				name = input[1].trim();
			} else if (input[0].startsWith("-quantity")) {
				quantity = input[1].trim();
			} else if (input[0].startsWith("-price")) {
				price = input[1].trim();
			} else if (input[0].startsWith("-type")) {
				type = input[1].trim();
			} else {
				System.out.println("Not a valid Input\n");
			} 
		}
		
		if (type.equalsIgnoreCase("raw")) {
			itemList.add(new RawItem(name, Integer.parseInt(price), Integer.parseInt(quantity)));
		} else if (type.equalsIgnoreCase("imported")) {
			itemList.add(new ImportedItem(name, Integer.parseInt(price), Integer.parseInt(quantity)));
		} else if (type.equalsIgnoreCase("manufactured")) {
			itemList.add(new ImportedItem(name, Integer.parseInt(price), Integer.parseInt(quantity)));
		} else {
			System.out.println("Not a valid Input\n");
		}
		
	}

	public static void main(String[] args) {
		boolean isContinuing = true;
		int i = 1;

		while (isContinuing) {
			System.out.println("Give input for Item " + i);
			
		    getInput();

		    boolean inputIsInvalid = true;
		    while (inputIsInvalid) {
		      System.out.print("Continue? (y/n): ");

		      String choice = sc.nextLine();

		      if ("y".equalsIgnoreCase(choice)) {
		    	  i++;
		          inputIsInvalid = false;
		      }
		      else if ("n".equalsIgnoreCase(choice)) {
		          inputIsInvalid = false;
		          isContinuing = false;
		      }
		      else {
		          System.out.print("Error: Only valid answers are Y/N.");
		      }
		    }
		}
		
		for(Item it: itemList) {
			System.out.println(it);
		}
		
		sc.close();
	}

}
