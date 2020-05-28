package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a class that basically makes operations with the hash map that contains
 * the counter of N_ijkc read from the train file. 
 */
public class Counter_map {
	
	/**
	 * count_map is a hash map that will store the N_ijkc values
	 */
	private Map<String, Double> count_map = new HashMap<>();
	
	/**
	 * nodesR is a vector of integers that will store the range of every feature
	 */
	int nodesR[];
	
	/**
	 * N is the number of the lines of the train file
	 */
	private int N;
	
	/**
	 * Counter_map constructor that initialize the nodesR 
	 * @param R_values range of every feature 
	 */
	public Counter_map(int R_values) {
		
		nodesR = new int[R_values];
	}
	
	/**
	 * fill_Counter_map is a method that clones the R_values to the local attribute node_R
	 * @param R_values range of every feature 
	 */
	public void fill_Counter_map(int[] R_values) {
		
		nodesR = R_values.clone();
	}
	
	
	/**
	 * put_hashmap is a method that puts or changes the values of the hash map
	 * @param key String with the hash map key
	 * @param value Double that we want to put in the hash map
	 */
	public void put_hashmap(String key, Double value) {
		count_map.computeIfPresent(key,(k,v) -> v+1);
		count_map.putIfAbsent(key,value);
	}
	
	
	/**
	 * getN_ikc is a method that searches in the hash map multiple key values, gets their sum, and then returns N_ikc
	 * @param key String with the hash map key of the type key(l-i-k-c)
	 * @return Double that contains the value of the key
	 */
	public Double getN_ikc(String key) {
		double N_aux = 0.0;
		String[] Nkey = key.split("-");
		int max = nodesR[Integer.parseInt(Nkey[0])];
		
		for(int j = 0; j <= max; j++) {
			if(this.count_map.containsKey(Nkey[0]+"-"+Nkey[1]+"-"+ j +"-"+Nkey[2]+"-"+Nkey[3])) {
				N_aux += this.count_map.get(Nkey[0]+"-"+Nkey[1]+"-"+ j +"-"+Nkey[2]+"-"+Nkey[3]);
			}
		}
		return N_aux;
	}
	

	/**
	 * getN_ijc is a method that searches in the hash map multiple key values, gets their sum, and then returns N_ijc
	 * @param key String with the hash map key of the type key(l-i-j-c)
	 * @return Double that contains the value of the key
	 */
	public Double getN_ijc(String key) {
		double N_aux = 0.0;
		String[] Nkey = key.split("-");
		int max = nodesR[Integer.parseInt(Nkey[1])];
		
		for(int k = 0; k <= max; k++) {
			if(this.count_map.containsKey(Nkey[0]+"-"+Nkey[1]+"-"+ Nkey[2] +"-"+ k +"-"+Nkey[3])) {
				N_aux += this.count_map.get(Nkey[0]+"-"+Nkey[1]+"-"+ Nkey[2] +"-"+ k +"-"+Nkey[3]);
				
			}
			
			
		}
		return N_aux;
	}
	
	/**
	 * getN_ijc is a method that searches in the hash map multiple key values, gets their sum, and then returns N_c
	 * @param key String with the hash map key of the type key(l-i-c)
	 * @return Double that contains the value of the key
	 */
	public Double getN_c(String key) {
		double N_aux = 0.0;
		String[] Nkey = key.split("-");
		int max_j = nodesR[Integer.parseInt(Nkey[0])];
		int max_k = nodesR[Integer.parseInt(Nkey[1])];
		
		for(int k = 0; k <= max_k; k++) {
			for(int j = 0; j <= max_j; j++)
			if(this.count_map.containsKey(Nkey[0]+"-"+Nkey[1]+"-"+ j +"-"+ k +"-"+Nkey[2])) {
				N_aux += this.count_map.get(Nkey[0]+"-"+Nkey[1]+"-"+ j +"-"+ k +"-"+Nkey[2]);
			}
		}
		return N_aux;
	}
	
	/**
	 * getN_ijc is a method that searches in the hash map multiple key values, gets their sum, and then returns N_c
	 * @param key String with the hash map key of the type key(l-i-j-k-c)
	 * @return Double that contains the value of the key
	 */
	public Double getN_ijkc(String key) {
		double N_aux = 0.0;
		if(this.count_map.containsKey(key)) {
			N_aux = this.count_map.get(key);
		
		}
		return N_aux;
	}

	/**
	 * getN is the method that returns the value of N
	 * @return N
	 */
	public double getN() {
		return N;
	}
	
	/**
	 * inc is the method that increments the number of lines in the file
	 */
	public void inc() {
		N++;
	}
	
	/**
	 * toString is the method that returns the attributes of this class
	 */
	@Override
	public String toString() {
		return "Counter_map [count_map=" + count_map + ", nodesR=" + Arrays.toString(nodesR) + ", N=" + N + "]";
	}
	
	

}
