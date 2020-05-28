package network_constructor;

import graph.DataGraph;

/**
 * The ML class implements the minimum description length (MDL)  
 * score and sets the wedges weight with this score. This method
 * extends the LL class since it only has to subtract a constant
 * from its scores.
 */
public class MDL extends LL{

	/**
	 * Method that sets the edges of the graph with the weight
	 * given by the MDL scoring criteria
	 * @param grafo corresponds to DataGraph object that is
	 * being trained
	 */
	@Override
	public void Set_score(DataGraph grafo) {
		for (i = 0; i < grafo.getNumberNodes() - 1; i++) {
			for (j = i + 1; j < grafo.getNumberNodes() - 1; j++) {
				initialization(grafo);
				hash_search(grafo);
				score_calc();
				grafo.getNodes(i).getEdges(j).setWeight(score);
			}
		}
	}

	/**
	 * Method that calculates the MDL score for a given edge that 
	 * connects the nodes i and j
	 */
	private void score_calc() {
		double score2 = ((s*(r-1)*(q-1))/2)*Math.log(prev_den);
		score = score - score2;
		
	}

}
