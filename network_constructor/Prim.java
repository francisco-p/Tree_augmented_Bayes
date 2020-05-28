package network_constructor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import graph.DataEdge;
import graph.DataGraph;
import graph.DataNode;

/**
 * This class implements the Prim algorithm, creating 
 *a maximal weighted undirected spanning tree
 */
public class Prim implements Algorithm {

	/**
	 * Graph in which the algorithm is applied
	 */
	private DataGraph graph;

	/**
	 *This method sets the graph which the algorithm is going to alter
	 *@param train_graph Graph that is being transformed
	 */
	@Override
	public void setGraph(DataGraph train_graph) {
		graph = train_graph;
	}


	/**
	 * Method that alters the graph. The final graph will have the edges utilized in 
	 * the tree set as Included
	 */
	@Override
	public void run() {
		if (graph == null) {
			return;
		}
		if (graph.size() > 0) {
			graph.getNodes(0).setVisited(true);
		}
		while (isDisconnected()) {
			DataEdge nextMaximum = new DataEdge(0, 0);
			nextMaximum.setWeight(Integer.MIN_VALUE);

			DataNode nextVertex = graph.getNodes(0);

			for (DataNode vertex : graph.getAllNodes()) {
				if (vertex.isVisited()) {
					Map<Integer, DataEdge> candidateMap = nextMaximum(vertex.getNumber());

					Map.Entry<Integer, DataEdge> candidate = candidateMap.entrySet().iterator().next();

					if (candidate.getValue().getWeight() > nextMaximum.getWeight()) {
						nextMaximum = candidate.getValue();
						nextVertex = graph.getNodes(candidate.getKey());
					}
				}
			}
			nextMaximum.setIncluded(true);
			nextVertex.setVisited(true);
		}

		removeEdges();
	}

	/**
	 * Method that returns true if the tree is still disconnected
	 */
	private boolean isDisconnected() {
		for (DataNode vertex : graph.getAllNodes()) {
			if (!vertex.isVisited()) {
				return true;
			}
		}
		return false;
	}

	

	/**
	 * Method that identifies the next next candidate max weighted edge that 
	 * might be included in the final tree
	 * @param next The next max weighted edge
	 */
	private Map<Integer, DataEdge> nextMaximum(int next) {

		DataEdge nextMax = new DataEdge(0, 0);
		nextMax.setWeight(Integer.MIN_VALUE);

		DataNode nextVertexNode = graph.getAllNodes().get(next);
		Integer nextVertex = nextVertexNode.getNumber();

		Iterator<Map.Entry<Integer, DataEdge>> it = nextVertexNode.getEdges().entrySet().iterator();

		while (it.hasNext()) {

			Entry<Integer, DataEdge> pair = it.next();

			if (!graph.getNodes(pair.getKey()).isVisited()) {

				if (!pair.getValue().isIncluded()) {

					if (pair.getValue().getWeight() > nextMax.getWeight()) {

						nextMax = pair.getValue();
						nextVertex = pair.getKey();
					}
				}
			}

		}

		Map<Integer, DataEdge> ret = new HashMap<>();
		ret.put(nextVertex, nextMax);

		return ret;

	}

	/**
	 * Method that concludes the tree creation by removing all
	 * the edges that were not included 
	 */
	public void removeEdges() {

		for (DataNode node : graph.getAllNodes()) {
			for (int i = 0; i < graph.getCount().getN(); i++) {
				if (node.getEdges(i) != null) {
					if (!node.getEdges(i).isIncluded()) {
						node.getEdges().remove(i);
					}
				}
			}

		}
		
	}
}





