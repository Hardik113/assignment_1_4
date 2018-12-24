package assignment.three;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
    public String name;
    private int id;
    private List<Node> comingInNodes;
    private List<Node> goingOutNodes;
    
    

    public Node(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	public void addComingInNode(Node node) {
        if (comingInNodes == null)
            comingInNodes = new ArrayList<Node>();
        comingInNodes.add(node);
    }

    public void addGoingOutNode(Node node) {
        if (goingOutNodes == null)
            goingOutNodes = new ArrayList<Node>();
        goingOutNodes.add(node);
    }

    public List<Node> getComingInNodes() {
        return comingInNodes;
    }

    public List<Node> getGoingOutNodes() {
        return goingOutNodes;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void removeParent(Node parent) {
		comingInNodes.remove(parent);
	}
	
	public void removeChild(Node child) {
		goingOutNodes.remove(child);
	}

	@Override
	public String toString() {
		return "Node [name=" + name + ", id=" + id + "]";
	}
    
	
    
    
}

