package assignment.two;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class MainApp {
	
	static String []input = {"Name", "Address", "Age", "RollNumber", "Courses"};
	
	static TreeSet<User> userList;
	static HashSet<Integer> rollNumberList;
	
	static Scanner sc = new Scanner(System.in);
	
	static void initialize() {
		try {
			File f = new File("/tmp/user.ser");
			
			rollNumberList = new HashSet<>();
			
			if(f.exists() && !f.isDirectory()) { 
				FileInputStream fileIn = new FileInputStream("/tmp/user.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				userList = (TreeSet<User>) in.readObject();
	         
				if (userList == null) {
					userList = new TreeSet<>();
				} else {
					for(User u: userList) {
						rollNumberList.add(u.getRollNumber());
					}
				}
				in.close();
				fileIn.close();
			} else {
				userList = new TreeSet<>();
				System.out.println("No Initial file");
			}
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("User class not found");
			c.printStackTrace();
			return;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void getAndValidateInput() {
		
		String name = "", address = "";
		int rollNumber = 0, age = 0, startIndex = 0;
		HashSet<String> courses = new HashSet<String>();
		boolean allValid = true;
		
		
		while (startIndex < input.length) {
			
			System.out.println("Please enter " + input[startIndex] + ":");
			
			if (input[startIndex].equalsIgnoreCase("name")) {
				name = sc.nextLine();
				if (!name.trim().isEmpty()) {
					startIndex++;
					allValid = true;
				} else {
					allValid = false;
					System.out.println("Not a valid Input");
					name = null;
				}
			} else if(input[startIndex].equalsIgnoreCase("address")) {
				address = sc.nextLine();
				if (!address.trim().isEmpty()) {
					startIndex++;
					allValid = true;
				} else {
					allValid = false;
					System.out.println("Not a valid Input");
					address = null;
				}
			} else if(input[startIndex].equalsIgnoreCase("age")) {
				if (sc.hasNextInt()) {
					age = sc.nextInt();
					startIndex++;
					allValid = true;
				} else {
					allValid = false;
					System.out.println("Not a valid Input");
				}
			} else if(input[startIndex].equalsIgnoreCase("rollnumber")) {
				if (sc.hasNextInt()) {
					rollNumber = sc.nextInt();
					if (rollNumberList.contains(rollNumber)) {
						allValid = false;
						System.out.println("User with rollnumber already present");
					} else {
						allValid = true;
						startIndex++;
					}
				} else {
					allValid = false;
					System.out.println("Not a valid Input");
				}
			} else if(input[startIndex].equalsIgnoreCase("courses")) {
				courses.clear();
				while(courses.size() < 6 && !sc.hasNext("exit")) {
					String courseInput = sc.nextLine();
					if (!courseInput.trim().isEmpty()) {
						courses.add(courseInput);
					}
				}
				if (courses.size() <= 6 && courses.size() >=4) {
					allValid = true;
					startIndex++;
				} else {
					allValid = false;
					System.out.println("Not a valid Input");
					courses.clear();
				}
			}
		}
		
		if (allValid) {
			userList.add(new User(name, age, address, rollNumber, courses));
		}
	}
	
	static void display() {
		
		displayUsers(userList);
		
		List<User> list = new ArrayList<User>(userList);
		
		System.out.print("Sort By:\n" +
			   	"1. Age (Ascending).\n" + 
				"2. Age (Descending).\n" + 
				"3. Address (Ascending)\n" + 
				"4. Address (Descending)\n" + 
				"5. RollNumber (Ascending)\n" + 
				"6. RollNumber (Descending)\n");
		
		switch(sc.nextLine()) {
			case "1":
				Collections.sort(list, new AgeAscComparator());
				break;
			case "2":
				Collections.sort(list, new AgeDscComparator());
				break;
			case "3":
				Collections.sort(list, new AddressAscComparator());
				break;
			case "4":
				Collections.sort(list, new AddressDscComparator());
				break;
			case "5":
				Collections.sort(list, new RollNumberAscComparator());
				break;
			case "6":
				Collections.sort(list, new RollNumberDscComparator());
				break;
		}
		
		displayUsers(list);
	}
	
	static void displayUsers(Collection<User> userList) {
		
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.print("Name \t RollNumber \t  Age \t Address \t Courses \n");
		System.out.println("------------------------------------------------------------------------------------------------------");
		for(User user: userList) {
			System.out.println(user);
		}
	}
	
	static void deleteUser() {
		int rollNumber;
		System.out.println("Provide Id of the user to be removed");
		if (sc.hasNextInt()) {
			rollNumber = sc.nextInt();
			if (rollNumberList.contains(rollNumber)) {
				userList.removeIf((user) -> user.getRollNumber() == rollNumber);
				rollNumberList.removeIf((thisRollNumber) -> thisRollNumber == rollNumber);
			} else {
				System.out.println("Roll Number does not exist");
			}
		} else {
			System.out.println("Not a valid Id");
		}
	}
	
	static void saveUserDetails() {
		 try {
	         FileOutputStream fileOut =
	         new FileOutputStream("/tmp/user.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(userList);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /tmp/user.ser\n");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	static void confirmAndExit() {
		System.out.println("Do you want to save any changes?");
		if (sc.nextLine().equalsIgnoreCase("y")) {
			saveUserDetails();
		}
		System.exit(0);
	}
	
	static void displayMenu() {
		System.out.print("Menu\n" +
					   	"1. Add User details.\n" + 
						"2. Display User details.\n" + 
						"3. Delete User details\n" + 
						"4. Save User details.\n" + 
						"5. Exit\n");
		switch(sc.nextLine()) {
			case "1":
				getAndValidateInput();
				break;
			case "2":
				display();
				break;
			case "3":
				deleteUser();
				break;
			case "4":
				saveUserDetails();
				break;
			case "5":
				confirmAndExit();
				break;
			default:
				System.out.println("Nothing matched");
		}
	}

	public static void main(String[] args) {
		initialize();
		do {
			displayMenu();
		} while(true);
	}

}
