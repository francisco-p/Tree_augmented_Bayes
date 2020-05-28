package graph;

import java.util.HashMap;

import java.util.Map;


/**
 * This is a class of the element node of the graph with also all the methods needed to
 * this class
 *
 */
public class DataNode {
	
	/**
	 * integer that has the number of the feature it represents 
	 */
	private int node_number;
	
	/**
	 * integer that stores the maximum value of the correspondent feature
	 */
	private int r;
	
	/**
	 * hash map that stores every edge that this node has
	 */
	private Map<Integer, DataEdge> edges = new HashMap<>();
	
	/**
	 * boolean used to check if the node is already visited or not
	 */
	private boolean isVisited = false;
	
	/**
	 * integer with the value of the father's node
	 */
	private int father;
	
	
	/**
	 * DataNode constructor that initialize the value of the node_number
	 * @param _node_number value of the feature this node represents
	 */
	public DataNode(int _node_number) {
		this.node_number = _node_number;
	}
	
	/**
	 * getEdges is a method that return the edge connecting this node and "i" node
	 * @param i value of the node we want to search for 
	 * @return value of the edge connecting this node and "i" node
	 */ 
	public DataEdge getEdges(int i) {
		return edges.get(i);
	}

	/**
	 * getEdges is a method that return the hash map that contains all the edges connected to this node
	 * @return hash map that contains every edge connected to this node
	 */
	public Map<Integer, DataEdge> getEdges() {
		return edges;
	}
	
	/**
	 * putR is the method that updates the value r (maximum value of this feature)
	 * @param i maximum value that appears in the training file for this feature
	 */
	public void putR(int i) {
		r = i + 1;
	}
	
	/**
	 * getR is the method that returns the value of r (maximum value of this feature)
	 * @return maximum value this node can get
	 */
	public int getR() {
		return r;
	}
	
	/**
	 * getNumber is the method that return the value of the node, i. e., the number of the feature
	 * @return integer that stores the value of the node
	 */
	public int getNumber() {
		return node_number;
	}

	/**
	 * toString is the method that returns the attributes of this class
	 */
	@Override
	public String toString() {
		return "DataNode [is visitd?" +isVisited + " father: " + father  +" node_number=" + node_number + ", r=" + r +", edges=" + edges + "]";
	}
	
	/**
	 * setVisited is a method that updates the attribute isVisited that represents 
	 * if the node was already included on the tree or not
	 * @param b is visited
	 */
	public void setVisited(boolean b) {
		isVisited = b;
		
	}
	
	/**
	 * isVisited is a method that return the value of the boolean isVisited
	 * @return boolean isVisited
	 */
	public boolean isVisited() {
		return isVisited;
	}
	
	/**
	 * setFather is the method that updates the value of this node's father
	 * @param i number of this node's father
	 */
	public void setFather(int i) {
		father = i;
	}
	
	/**
	 * getFather is the method that returns the value of this node's father
	 * @return has father
	 */
	public int getFather() {
		return father;
	}
	
}
