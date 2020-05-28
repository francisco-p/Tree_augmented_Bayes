package classification;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class calculate a Confusion_Matrix using the array lists
 * build from the file.
 *
 */
public class Performance {
	/**
	 * Confusion Matrix will have one more row and column
	 * to store the sum of columns and rows
	 */
	private int[][] matrix;
	/**
	 * Size of the confusion matrix
	 */
	private int size;
	
	/**
	 * Classifier Accuracy 
	 */
	private Accuracy acc;
	
	/**
	 * Classifier Sensitivity 
	 */
	private Sensitivity sens;
	
	/**
	 * Classifier Specificity 
	 */
	private Specificity spec;
	
	/**
	 * Classifier F1score 
	 */
	private F1score f1;
	
	/**
	 * Classifier Precision 
	 */
	private Precision prec;
	/**
	 * Confusion_Matrix constructor
	 * @param i The number of variables
	 */
	public Performance(int i) {

		this.matrix = new int[i + 1][i + 1];
		this.size = i;
	}
	/**
	 * fill_matrix is a method used to fill the confusion matrix
	 * @param true_val True values of each class
	 * @param clas_val Classification made from the TAN
	 */
	public void fill_matrix(ArrayList<Integer> true_val, ArrayList<Integer> clas_val) {

		Iterator<Integer> i = clas_val.iterator();
		Iterator<Integer> j = true_val.iterator();
		while (i.hasNext()) {
			int i_current = i.next(), j_current = j.next();
			matrix[i_current][j_current]++;
			matrix[size][j_current]++;
			matrix[i_current][size]++;
			matrix[size][size]++;
		}
	}
	
	/**
	 * Method that calculates all the performance classifiers
	 */
	public void resume( ) {
		
	    
	    this.acc = new Accuracy();
	    this.acc.Calculation(this.size, this.matrix);
		
		this.spec = new Specificity(this.size);
		this.spec.Calculation(this.size, this.matrix);
	    
		this.sens = new Sensitivity(this.size);
		this.sens.Calculation(this.size, this.matrix);
		
		this.prec = new Precision(this.size);
		this.prec.Calculation(this.size, this.matrix);
		
		this.f1 = new F1score(this.size);
		this.f1.Calculation(this.size, this.matrix);
	}
	
	/**
	 *To string method
	 */
	public String toString() {
		
		String print = "";
		print += acc.toString();
		print += spec.toString();
		print += sens.toString();
		print += f1.toString();
		print += prec.toString();
	
		
		
		return print;
		
		
	}

	

}
