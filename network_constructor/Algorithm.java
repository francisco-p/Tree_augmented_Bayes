package network_constructor;

import graph.DataGraph;

/**
 * This is an interface that contains a collection of abstract methods needed
 * to build an algorithm to find a spanning tree for weighted undirected graph.
 */
public interface Algorithm {
	
	/**
	 * run is the method that executes the algorithm the algorithm
	 */
	public void run();
	
	/**
	 * setGraph is the method used to set the graph in which the algorithm will run.
	 * @param train_graph graph
	 */
	public void setGraph(DataGraph train_graph);
	
	/**
	 * removeEdges is the method used to remove the edges of the fully connected graph,
	 * that are not included in the final tree .
	 */
	public void removeEdges();

}
