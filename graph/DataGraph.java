package graph;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * DataGraph it is a class that build the graph used
 * to build the network constructor
 *
 */
public class DataGraph {

	/**
	 * Number of features
	 */
	private int NumberNodes;
	/**
	 * Range of class
	 */
	private int s;
	/**
	 * Name of each feature
	 */
	private ArrayList<String> Features = new ArrayList<String>();
	/**
	 * Array list of all nodes
	 */
	private ArrayList<DataNode> nodes = new ArrayList<DataNode>();
	/**
	 * Map used to count associations between nodes
	 */
	private Counter_map count;

	/**
	 * * Constructor of Graph class used to initialize the number of nodes and
	 * edges.
	 * @param number number of nodes
	 * @param features name of each feature
	 */
	public DataGraph(int number, String[] features) {

		this.NumberNodes = number;

		count = new Counter_map(number);
		// node initialization
		for (int i = 0; i < (number - 1); i++) {
			this.nodes.add(new DataNode(i));
			this.Features.add(features[i]);
		}
		this.Features.add(features[features.length - 1]);
	}

	/**
	 * Initialize all edges in the hash map
	 */
	public void initedges() {
		for (int i = 0; i < this.NumberNodes - 1; i++) {
			for (int j = i + 1; j < this.NumberNodes - 1; j++) {
				DataEdge edge = new DataEdge(i, j);
				this.nodes.get(i).getEdges().put(j, edge);
				this.nodes.get(j).getEdges().put(i, edge);
			}
		}
	}

	/**
	 * Initialize the counter
	 */
	public void init_counter() {

		int[] R_val = new int[this.NumberNodes - 1];
		int j = 0;
		Iterator<DataNode> i = nodes.iterator();

		while (i.hasNext()) {
			R_val[j] = i.next().getR();
			j++;
		}

		count.fill_Counter_map(R_val);

	}

	/**
	 * @param i number of node
	 * @return The DataNode wanted
	 */
	public DataNode getNodes(int i) {
		return nodes.get(i);
	}

	/**
	 * @return the number of nodes
	 */
	public int getNumberNodes() {
		return NumberNodes;
	}

	/**
	 * @param i range of class variable
	 */
	public void putS(int i) {
		s = i + 1;
	}

	/**
	 * @return return range of class
	 */
	public int getS() {
		return s;
	}

	/**
	 * @return the Count map
	 */
	public Counter_map getCount() {
		return count;
	}

	/**
	 * @return the size of graph
	 */
	public int size() {
		return nodes.size();
	}

	/**
	 * @return all nodes
	 */
	public ArrayList<DataNode> getAllNodes() {
		return nodes;
	}

	/**
	 * @return all edges
	 */
	public int getNumberEdges() {
		Integer ret = 0;

		for (DataNode node : nodes) {
			for (int i = 0; i < count.getN(); i++) {
				if (node.getEdges(i) != null) {
					if (node.getEdges(i).isIncluded()) {
						ret++;
					}
				}
			}

		}
		return ret;
	}

	/**
	 * @return The number of visited nodes
	 */
	public int getNumberSetNodes() {
		Integer ret = 0;

		for (DataNode node : nodes) {
			if (node.isVisited()) {
				ret++;
			}
		}
		return ret;
	}

	/**
	 * ToString method
	 */
	@Override
	public String toString() {
		String print = "Classifier:		";

		for (int i = 0; i < this.getNumberNodes() - 1; i++) {

			if (this.getNodes(i).getFather() == -1) {
				print += (this.Features.get(i) + " : " + this.Features.get(NumberNodes - 1) + "\n\t\t\t");
			} else {
				print += (this.Features.get(i) + " : " + this.Features.get(NumberNodes - 1) + " "
						+ this.Features.get((this.getNodes(i).getFather())) + "\n\t\t\t");
			}
		}
		print += this.Features.get(NumberNodes - 1) + " :\n\t\t\t";

		return print;
	}
}
