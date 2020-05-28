package network_constructor;

import graph.DataGraph;

/**
 * This is an interface that contains a collection of abstract methods needed
 * to build a network
 */
public interface INetwork {
	
	/**
	 * Set the graph previously built
	 * @param graph  Graph
	 */
	public void setGraph(DataGraph graph);
	
	/**
	 * Method that construct the network 
	 */
	public void Network_construct();

}
