package network_constructor;

import java.util.Random;
import java.util.Set;

import graph.DataEdge;
import graph.DataGraph;
import graph.DataNode;

import java.util.ArrayList;

/**
 * This class constructs a Tree Augmented tree from a graph
 *
 */
public class TAN implements INetwork{
	
	/**
	 * Graph previously built
	 */
	private DataGraph train_graph;
	
	/**
	 * Set the graph previously built
	 */
	public void setGraph(DataGraph graph) {
		this.train_graph = graph;
		
	}

	/**
	 * Constructs the TAN network
	 */
	public void Network_construct() {
		
		Random rand = new Random();
		
		int N = (int)train_graph.getNumberNodes();
		
		 int int_random = rand.nextInt(N-1);
		 
		 ArrayList<Integer> current_children = new ArrayList<Integer>();
		 ArrayList<Integer> next_children = new ArrayList<Integer>();
		 
		 int [] done = new int[N-1];
		 
		 
		 DataNode auxNode = train_graph.getNodes(int_random);
		 DataEdge auxEdge;
		 
		 done[int_random] = 1;
		 
		 train_graph.getNodes(int_random).setFather(-1);
		 
		 current_children.add(int_random);
	
		 int i = 1; 
		 
		 
		 while(i < N-1 || !current_children.isEmpty()) {
			 
			 while(!current_children.isEmpty()){
				 
				 
				 int current = current_children.get(0);
				 auxNode = train_graph.getNodes(current);
				 
				 Set <Integer> edges = auxNode.getEdges().keySet();
				 
				 for(Integer edge: edges) {
					 
					 if (done[edge] == 0) {
						 
						 auxEdge = train_graph.getNodes(current).getEdges(edge);
						 
						 if(auxEdge.getEndNodes()[0] != current) {
		
							 train_graph.getNodes(current).getEdges(edge).switchNode(); 
						 }
						 
						 train_graph.getNodes(edge).setFather(current);
						 
						 next_children.add(edge);
					 }	
				 }
				 done[current] = 1;
				 i++;
				 current_children.remove(0); 
			 }
			 
			 current_children = new ArrayList<>(next_children);
			 next_children.clear();
		 }	
	}

}