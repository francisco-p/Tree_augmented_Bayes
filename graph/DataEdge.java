package graph;


/**
 * This is a class of the element edge of the graph with also all the methods needed to
 * this class
 */
public class DataEdge {
	
	/**
	 * double that stores the weigh or alpha of the edge
	 */
	double weight;
	
	
	/**
	 * boolean used to check if the edge is used in the final tree or not
	 */
	private boolean isIncluded = false;
	
	/**
	 * vector of integers that stores the nodes that are connected throw this edge
	 */
	int [] end_nodes = new int[2];
	
	/**
	 * DataEdge constructor that initialize the nodes connected by this edge
	 * @param _node1 node connected throw this edge
	 * @param _node2 node connected throw this edge
	 */
	public DataEdge(int _node1, int _node2) {
		
		this.end_nodes[0] = _node1;
		this.end_nodes[1] = _node2;
		
	}
	
	/**
	 * setWeight is a method that updates the weigh or alpha of the edge
	 * @param weight value of alpha
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * getWeight is a method that return the value of weight
	 * @return double that stores the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * setIncluded is a method that updates the attribute isIncluded that represents 
	 * if the edge is included on the tree or not
	 * @param b boolean that represents if the edge is included on the tree or not
	 */
	public void setIncluded(boolean b) {
		isIncluded = b;
		
	}
	
	/**
	 * isIncluded is a method that return the value of the boolean isIncluded
	 * @return boolean isIncluded
	 */
	public boolean isIncluded() {
		return isIncluded;
		
	}
	
	/**
	 * getEndNodes is a method that return the value of the nodes connected by this edge
	 * @return integer vector with the nodes connected by this edge
	 */
	public int[] getEndNodes(){
		return end_nodes;
	}
	
	/**
	 * switchNode is a method to invert the order in which the is orientated
	 */
	public void switchNode() {
				
		int aux0 = this.end_nodes[0];
		
		int aux1 = this.end_nodes[1];
		
		this.end_nodes[0] = aux1;
		this.end_nodes[1] = aux0;
		
		
	}





	

}