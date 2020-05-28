package main;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

import graph.DataGraph;

/**
 * Train_action is class that extend from the abstract class File_Reader. This
 * class has the goal of reading and building the train graph.
 */
public class Train_action extends File_Reader {
	/**
	 * Auxiliary vector the stores the range of the Features
	 */
	private int[] aux;
	
	/**
	 * Graph that stores all the nodes and edges connected
	 */
	private DataGraph train_graph;

	/**
	 * Train_action Constructor
	 * 
	 * @param f receives a file to be read
	 */
	public Train_action(String f) {
		super(f);
	}

	/**
	 * Read the first line a build the graph nodes
	 */
	protected void fillvector() {
		try {
			Scanner scanIn = new Scanner((new FileReader(this.file)));
			String InputLine = scanIn.nextLine();
			String[] InArray = InputLine.split(",");
			this.NumberOfFeatures = InArray.length;
			this.Names = InArray.clone();
			aux = new int[this.NumberOfFeatures];
			
			train_graph = new DataGraph(NumberOfFeatures,Names);

			scanIn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
	}

	/**
	 * Read the file a count the correspondence between each nodes
	 */
	@Override
	protected void fileaction(String[] InArray, DataGraph grafo) {
		
		try {
			for(int i = 0; i<InArray.length; i++)  Integer.parseInt(InArray[i]);
		} catch ( NumberFormatException e) {
			System.out.println("WARNING: File "+ this.file + " contains line with invalid integer");
			return;
		}

		for (int i = 0; i < InArray.length - 1; i++) {
			for (int j = 0; j < InArray.length - 1; j++) {
				if (i == j)
					continue;
				grafo.getCount().put_hashmap(
						j + "-" + i + "-" + InArray[j] + "-" + InArray[i] + "-" + InArray[InArray.length - 1], 1.0);

			}
			// Calculate the range of the values
			setR(i, InArray);
			grafo.getNodes(i).putR(aux[i]);
		}
		// Calculate the range of c
		setS(InArray);
		grafo.putS(aux[InArray.length - 1]);
		grafo.getCount().inc();

	}

	/**
	 * Set each node range
	 * 
	 * @param i       possible range
	 * @param InArray actual range
	 */
	private void setR(int i, String[] InArray) {
		if (Integer.parseInt(InArray[i]) > aux[i]) {
			aux[i] = Integer.parseInt(InArray[i]);

		}
	}

	/**
	 * Set classification range
	 * 
	 * @param InArray classification range
	 */
	private void setS(String[] InArray) {
		if (Integer.parseInt(InArray[InArray.length - 1]) > aux[InArray.length - 1]) {
			aux[InArray.length - 1] = Integer.parseInt(InArray[InArray.length - 1]);

		}
	}
	

	public DataGraph getTrain_graph() {
		return train_graph;
	}

	@Override
	public String toString() {
		return "Train_action [Names=" + Arrays.toString(Names) + "]";
	}
	
}