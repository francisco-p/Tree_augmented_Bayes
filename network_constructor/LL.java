package network_constructor;

import graph.DataGraph;


/**
 * The LL class implements the log-likelihood (LL) score 
 * and sets the wedges weight with this score
 */
public class LL implements IModel_Selection {

	/**
	 * Number of configurations of node j
	 */
	protected int q; 
	/**
	 * Number of configurations of node i
	 */
	protected int r;
	/**
	 * Number of configurations of class c
	 */
	protected int s;
	/**
	 * Node 1 of the edge
	 */
	protected int i; 
	/**
	 * Node 2 of the edge
	 */
	protected int j;
	/**
	 * Auxiliary attribute for the score formula 
	 * that saves the value N_ijkc
	 */
	protected double prev_num;
	/**
	 * Auxiliary attribute for the score formula
	 * that saves the value N
	 */
	protected double prev_den;
	/**
	 * Auxiliary attribute for the score formula 
	 * that saves the value N_ijkc * N_c
	 */
	protected double numerator;
	/**
	 * Auxiliary attribute for the score formula 
	 * that saves the value N_ikc * N_ijc
	 */
	protected double denominator;
	/**
	 * Auxiliary attribute for the score formula 
	 * that saves the value log_2(numerator/denominator)
	 */
	protected double deps;
	/**
	 * returning value of the LL score
	 */
	protected double score;

	
	/**
	 * Method that sets the edges of the graph with the weight
	 * given by the LL scoring criteria
	 * @param grafo corresponds to DataGraph object that is
	 * being trained
	 */
	@Override
	public void Set_score(DataGraph grafo) {
		for (i = 0; i < grafo.getNumberNodes() - 1; i++) {
			for (j = i + 1; j < grafo.getNumberNodes() - 1; j++) {
				initialization(grafo);
				hash_search(grafo);
				grafo.getNodes(i).getEdges(j).setWeight(score);
			}
		}
	}
	
	/**
	 * Method that calculates the LL score for a given edge that 
	 * connects the nodes i and j
	 * @param grafo corresponds to DataGraph object that is
	 * being tested
	 */
	protected void hash_search(DataGraph grafo) {
		for (int auxj = 0; auxj < q; auxj++) {
			for (int auxk = 0; auxk < r; auxk++) {
				for (int auxc = 0; auxc < s; auxc++) {
					prev_num = (grafo.getCount().getN_ijkc(j + "-" + i + "-" + auxj + "-" + auxk + "-" + auxc));
					if (prev_num == 0.0)
						continue;

					prev_den = (grafo.getCount().getN());

					numerator = (prev_num * grafo.getCount().getN_c(j + "-" + i + "-" + auxc));
					if (numerator == 0.0)
						continue;

					denominator = (grafo.getCount().getN_ikc(j + "-" + i + "-" + auxk + "-" + auxc)
							* grafo.getCount().getN_ijc(j + "-" + i + "-" + auxj + "-" + auxc));
					
					//if (denominator == 0.0)
						//continue;

					logarithm();
					
					
					score = score + (prev_num / prev_den) * deps;

				}
			}
		}
	}
	
	/**
	 * Method that initializes the attributes that correspond 
	 * to the nodes i and j
	 * @param grafo corresponds to DataGraph object that is
	 * being tested
	 */
	protected void initialization(DataGraph grafo) {
		q = grafo.getNodes(j).getR();
		r = grafo.getNodes(i).getR();
		s = grafo.getS();
		prev_num = 0;
		prev_den = 0;
		numerator = 0;
		denominator = 0;
		deps = 0;
		score = 0;
	}
	

	/**
	 * Method that supplements the score formula by calculating
	 *  the logarithm of base 2 of the value numerator/denominator
	 */
	private void logarithm() {
		try {
			if (denominator == 0.0) throw new DivisionByZero();
			
			this.deps = numerator / denominator;
			
			if (this.deps == 0) throw new LogarithmOfZero();

			this.deps = Math.log10(deps) / Math.log10(2);
			
		} catch( DivisionByZero e){
			this.deps = 0;
			
		} catch (LogarithmOfZero e) {
			this.deps = 0;
			
		}
		
		
	}



}
