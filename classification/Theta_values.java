package classification;

import java.util.HashMap;
import java.util.Map;

import graph.DataGraph;
import network_constructor.DivisionByZero;

/**
 * This class responsibility is calculating the probabilities, in this way it
 * stores the values in an Hash Map
 *
 */
public class Theta_values {
	/**
	 * Hash Map that receives a String and a double. The string is a key to access a
	 * wanted theta values and the double is its value.
	 */
	private Map<String, Double> theta_map;
	
	/**
	 *  Pseudo counts that avoid the mistake of assigning probability zero to
	 *  an extremely unlikely event
	 */
	private static double PseudoN; 

	/**
	 * Theta_values Constructor Initialize the HashMap
	 */
	public Theta_values() {
		this.theta_map = new HashMap<>();
	}

	/**
	 * This method put the wanted theta value in the hash_map
	 * @param l Parent node
	 * @param i Current node
	 * @param j Parent node value
	 * @param k Current node value
	 * @param c Classification value
	 * @param graph TAN structure
	 * @return a double value of theta
	 */
	public Double setThetaijkc(int l, int i, int j, int k, int c, DataGraph graph) {

		String key;
		Double Nijkc;
		Double Nijc;
		Double theta;

		if (l == -1 && i != 0) {
			Nijkc = graph.getCount().getN_ikc("0-" + i + "-" + k + "-" + c);

			key = i + "-1-" + k + "-" + c;

			Nijc = graph.getCount().getN_c("0-" + i + "-" + c);

		} else if (l == -1 && i == 0) {
			Nijkc = graph.getCount().getN_ikc("1-" + i + "-" + k + "-" + c);

			key = i + "-1-" + k + "-" + c;

			Nijc = graph.getCount().getN_c("1-" + i + "-" + c);

		} else {

			Nijkc = graph.getCount().getN_ijkc(l + "-" + i + "-" + j + "-" + k + "-" + c);
			key = i + "-" + j + "-" + k + "-" + c;
			Nijc = graph.getCount().getN_ijc(l + "-" + i + "-" + j + "-" + c);
		}
		Double den = Nijc + graph.getNodes(i).getR() * Theta_values.PseudoN;
		try {
			if (den == 0) throw new DivisionByZero();
			theta = (Nijkc + Theta_values.PseudoN) / den;
			
		} catch(DivisionByZero e) {
			theta = 0.00;
			System.out.println("ERROR - Division by zero while calculating theta_ijkc: " + i + "-" + j + "-" + k + "-" + c);
		}
		
		

		theta_map.put(key, theta);

		return theta;
	}
	/**
	 * This method put the wanted theta value in the hash_map
	 * @param c Classification value
	 * @param graph TAN structure
	 * @return  a double value of theta
	 */
	public Double setThetac(int c, DataGraph graph) {

		Double Nc;
		Double theta;

		Nc = graph.getCount().getN_c("0-1-" + c);
		Double den = graph.getCount().getN() + graph.getS() * Theta_values.PseudoN;
		
		try {
			if (den == 0) throw new DivisionByZero();
			theta = (Nc + Theta_values.PseudoN) / den;
			
		} catch(DivisionByZero e) {
			theta = 0.00;
			System.out.println("ERROR - Division by zero while calculating theta_c: " + c);
		}

		theta_map.put(Integer.toString(c), theta);

		return theta;
	}
	/**
	 * Check if the wanted theta value is already in the hash map
	 * @param key String key of the theta value
	 * @return A boolean indicating if its true or not
	 */
	public Boolean Contains(String key) {

		boolean bol = this.theta_map.containsKey(key);
		return bol;
	}
	/**
	 * Get the theta value for the wanted key
	 * @param key String key of the theta value
	 * @return The value wanted
	 */
	public double Get_val(String key) {

		Double val = this.theta_map.get(key);
		return val;
	}
	
	/**
	 * Method that sets the pseudo-counts 
	 * @param pseudoN are the pseudo-counts 
	 */
	public static void setPseudoN(double pseudoN) {
		PseudoN = pseudoN;
	}
	

}
