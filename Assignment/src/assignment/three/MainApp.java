package assignment.three;

import java.util.Scanner;

public class MainApp {
	
	static Scanner sc = new Scanner(System.in);
	static private Graph graph;
	
	static void addNode() {
		String name;
		int id;
		
		System.out.println("Enter Node name:");
		name = sc.nextLine();
		
		System.out.println("Enter Node id:");
		id = sc.nextInt();
		
		if (graph.isUniqueId(id)) {
			graph.createNode(id, name);
		} else {
			System.out.println("Node with id " + id + " already exists" );
		}
	}
	
	static void addDependency() {
		int firstId, secondId;
		
		System.out.println("Enter First Node id:");
		firstId = sc.nextInt();
		
		System.out.println("Enter Second Node id:");
		secondId = sc.nextInt();
		
		if (graph.contains(firstId) && graph.contains(secondId)) {
			graph.addDependency(firstId, secondId);
		} else {
			System.out.println("Node does not exist");
		}
	}
	
	static void getImmediateParents() {
		 int id;
		 
		 System.out.println("Enter the id of the node");
		 id = sc.nextInt();
		 
		 for(Node node: graph.getNode(id).getComingInNodes()) {
			 System.out.println(node);
		 }
	}
	
	static void getImmediateChildren() {
		 int id;
		 
		 System.out.println("Enter the id of the node");
		 id = sc.nextInt();
		 
		 for(Node node: graph.getNode(id).getGoingOutNodes()) {
			 System.out.println(node);
		 }
	}
	
	static void getAncestors() {
		int id;
		 
		System.out.println("Enter the id of the node");
		id = sc.nextInt();
		 
		graph.generateDependencies(graph.getNode(id).getComingInNodes()); 
	}
	
	static void getDescendants() {
		int id;
		 
		System.out.println("Enter the id of the node");
		id = sc.nextInt();
		 
		graph.generateDependencies(graph.getNode(id).getGoingOutNodes()); 
	}
	
	static void deleteDependency() {
		int id;
		
		System.out.println("Enter the id of the node");
		id = sc.nextInt();
		
		Node currentNode = graph.getNode(id);
		
		for(Node node: currentNode.getComingInNodes()) {
			node.removeChild(currentNode);
		}
		
		for(Node node: currentNode.getGoingOutNodes()) {
			node.removeParent(currentNode);
		}
	}

	static void displayMenu() {
		System.out.print("Menu\n" +
					   	"1. Add Node.\n" + 
						"2. Add Dependency.\n" + 
						"3. Get Immediate Parent Nodes\n" + 
						"4. Get Immediate Children Nodes\n" + 
						"5. Get Ancestor Nodes\n" + 
						"6. Get Descendants Nodes\n" +
						"7. Delete Dependecies\n" +
						"8. Exit\n");
		switch(sc.nextLine()) {
			case "1":
				addNode();
				break;
			case "2":
				addDependency();
				break;
			case "3":
				getImmediateParents();
				break;
			case "4":
				getImmediateChildren();
				break;
			case "5":
				getAncestors();
				break;
			case "6":
				getDescendants();
				break;
			case "7":
				deleteDependency();
				break;
			default:
				System.out.println("Nothing matched");
		}
	}

	public static void main(String[] args) {
		graph = new Graph(new NodeValueListener() {

			@Override
			public void evaluating(Node nodeValue) {
				System.out.println(nodeValue);
				
			}
        });
		
		do {
			displayMenu();
		} while(true);
	}

}
