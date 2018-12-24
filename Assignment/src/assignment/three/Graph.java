package assignment.three;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
	
	private HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
	
	private HashSet<Integer> nodeIdSet = new HashSet<>();

    private NodeValueListener listener;

    private List<Node> evaluatedNodes = new ArrayList<Node>();


    public Graph(NodeValueListener listener) {
        this.listener = listener;
    }

    public void addDependency(int evalFirstId, int evalSecondId) {
        Node firstNode = null;
        Node afterNode = null;
        if (nodes.containsKey(evalFirstId)) {
            firstNode = nodes.get(evalFirstId);
        } else {
            System.out.println("Node with" + evalFirstId + " does not exist");
            return;
        }
        if (nodes.containsKey(evalSecondId)) {
            afterNode = nodes.get(evalSecondId);
        } else {
        	System.out.println("Node with" + evalSecondId + " does not exist");
            return;
        }
        firstNode.addGoingOutNode(afterNode);
        afterNode.addComingInNode(firstNode);
    }

    public void createNode(int id, String name) {
        Node node = new Node(name, id);
        nodeIdSet.add(id);
        nodes.put(id, node);
        
        System.out.println(nodes);
        System.out.println(nodeIdSet);
    }

    public void generateDependencies() {
        List<Node> orphanNodes = getOrphanNodes();
        List<Node> nextNodesToDisplay = new ArrayList<Node>();
        for (Node node : orphanNodes) {
            listener.evaluating(node);
            evaluatedNodes.add(node);
            nextNodesToDisplay.addAll(node.getGoingOutNodes());
        }
        generateDependencies(nextNodesToDisplay);
    }

    public void generateDependencies(List<Node> nodes) {
        List<Node> nextNodesToDisplay = null;
        for (Node node : nodes) {
            if (!isAlreadyEvaluated(node)) {
                List<Node> comingInNodes = node.getComingInNodes();
                if (areAlreadyEvaluated(comingInNodes)) {
                    listener.evaluating(node);
                    evaluatedNodes.add(node);
                    List<Node> goingOutNodes = node.getGoingOutNodes();
                    if (goingOutNodes != null) {
                        if (nextNodesToDisplay == null)
                            nextNodesToDisplay = new ArrayList<Node>();
                        nextNodesToDisplay.addAll(goingOutNodes);
                    }
                } else {
                    if (nextNodesToDisplay == null)
                        nextNodesToDisplay = new ArrayList<Node>();
                    nextNodesToDisplay.add(node);
                }
            }
        }
        if (nextNodesToDisplay != null) {
            generateDependencies(nextNodesToDisplay);
        }
    }

    private boolean isAlreadyEvaluated(Node node) {
        return evaluatedNodes.contains(node);
    }

    private boolean areAlreadyEvaluated(List<Node> nodes) {
        return evaluatedNodes.containsAll(nodes);
    }

    private List<Node> getOrphanNodes() {
        List<Node> orphanNodes = null;
        Set<Integer> keys = nodes.keySet();
        for (int key : keys) {
            Node node = nodes.get(key);
            if (node.getComingInNodes() == null) {
                if (orphanNodes == null)
                    orphanNodes = new ArrayList<Node>();
                orphanNodes.add(node);
            }
        }
        return orphanNodes;
    }
    
    public boolean isUniqueId(int id) {
    	if (!nodeIdSet.contains(id)) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public Node getNode(int id) {
    	return nodes.get(id);
    }

    public boolean contains(int id) {
    	if (nodes.containsKey(id)) {
    		return true;
    	} else {
    		return false;
    	}
    }
}