package network_constructor;

import graph.DataGraph;

/**
 * This is an interface that contains a collection of abstract methods needed
 * to build a scoring criteria to put weights in the edges
 */
public interface IModel_Selection {
	/**
	 * Method that sets the edges of the graph with the weight
	 * given by a scoring criteria
	 * @param grafo corresponds to DataGraph object that is
	 * being trained
	 */
	void Set_score(DataGraph grafo);

}
