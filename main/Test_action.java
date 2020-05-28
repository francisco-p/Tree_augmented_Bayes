package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import classification.Performance;
import classification.Theta_values;
import graph.DataGraph;

/**
 * Test_action is class that extend from the abstract class File_Reader. This
 * class has the goal of reading and classify.
 */
public class Test_action extends File_Reader {

	/**
	 * ArrayList to store the classification values
	 */
	private ArrayList<Integer> class_test = new ArrayList<Integer>();

	/**
	 * ArrayList to store the true values
	 */
	private ArrayList<Integer> true_val = new ArrayList<Integer>();
	
	/**
	 * Hash map to store the theta values
	 */
	Theta_values theta_map = new Theta_values();
	
	private Performance perf;
	/**
	 * @param f file to be read
	 */
	public Test_action(String f) {
		super(f);
		
	}

	/**
	 * Read the file and classify the features
	 */
	@Override
	protected void fileaction(String[] InArray, DataGraph graph) {

		int aux_int[] = new int[InArray.length];
		try {
			aux_int = Arrays.stream(InArray).mapToInt(Integer::parseInt).toArray();	
		} catch(NumberFormatException e) {
			System.out.println("WARNING: File "+ this.file + " contains line with invalid integer");
			return;
		}
		
		
		true_val.add(aux_int[InArray.length - 1]);
		class_test.add(probability_calculation(aux_int, graph));

	}

	/**
	 * Calculates the probability in each class
	 * 
	 * @param val   values of the different features
	 * @param graph TAN structure
	 * @return The class predicted
	 */
	private int probability_calculation(int[] val, DataGraph graph) {

		int[] aux = new int[val.length];
		double[] prob_conj = new double[graph.getS()];
		ArrayList<Double> des = new ArrayList<Double>();
		double sum = 0;
		double max = 0;
		int index = 0;

		aux = val.clone();

		for (int i = 0; i < graph.getS(); i++) {
			aux[aux.length - 1] = i;
			prob_conj[i] = class_decision(aux, graph);
			sum = sum + prob_conj[i];
		}
		for (int i = 0; i < graph.getS(); i++) {

			des.add(prob_conj[i] / sum);
		}

		max = Collections.max(des);
		index = des.indexOf(max);

		return index;
	}

	/**
	 * Calculate the thetas values correspondent to the line read
	 * 
	 * @param val   values of the different features
	 * @param graph graph TAN structure
	 * @return the probability
	 */
	private double class_decision(int[] val, DataGraph graph) {

		Double aux_theta[] = new Double[val.length];

		String key;

		int father;

		double decision1 = 0;

		for (int i = 0; i < val.length - 1; i++) {

			father = graph.getNodes(i).getFather();

			if (i == val.length - 1) {
				key = Integer.toString(val[i]);
			} else if (father == -1) {
				key = i + "-1-" + val[i] + "-" + val[val.length - 1];
			} else {
				key = i + "-" + val[father] + "-" + val[i] + "-" + val[val.length - 1];
			}

			if (theta_map.Contains(key)) {
				aux_theta[i] = theta_map.Get_val(key);
			} else if (father == -1) {
				aux_theta[i] = theta_map.setThetaijkc(father, i, 0, val[i], val[val.length - 1], graph);
			} else {
				aux_theta[i] = theta_map.setThetaijkc(father, i, val[father], val[i], val[val.length - 1], graph);
			}

		}
		key = Integer.toString(val[val.length - 1]);

		if (theta_map.Contains(key)) {
			aux_theta[val.length - 1] = theta_map.Get_val(key);
		} else {
			aux_theta[val.length - 1] = theta_map.setThetac(val[val.length - 1], graph);
		}

		decision1 = aux_theta[0];
		for (int x = 1; x < aux_theta.length; x++) {
			decision1 = decision1 * aux_theta[x];
		}

		for (int j = 0; j < aux_theta.length; j++) {
		}

		return decision1;
	}

	/**
	 * @return Classification values
	 */
	public ArrayList<Integer> getClass_test() {
		return class_test;
	}

	/**
	 * @return True values
	 */
	public ArrayList<Integer> getTrue_val() {
		return true_val;
	}
	
	public void setPerformance(int s){
		this.perf = new Performance(s);
		
	}

	public Performance getPerf() {
		return perf;
	}

	/**
	 * ToString method
	 */
	@Override
	public String toString() {
		String print = "\nTesting the classifier: ";
		int j= 1;
		Iterator<Integer> i = this.class_test.iterator();
		while (i.hasNext()) {
			   print += "instance "+ j + ":\t\t"+ i.next() + "\n\t\t\t";
			   j++;
		}
		
		return print;
	}
	
}