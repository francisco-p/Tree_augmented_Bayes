package classification;

import java.text.DecimalFormat;

import network_constructor.DivisionByZero;

/**
 * Precision is a class that implements the abstract class
 * Performance_classifiers.
 */
public class Precision implements Performance_classifiers {

	/**
	* Value of the Precision in each class
	*/
	private double[] precision;
	/**
	* Precision is a double where is stored the specificity weighted average of the
	 * classes
	*/
	private double precision_wa;

	/**
	 * @param size  Size of the Precision vector
	 */
	public Precision(int size) {
		precision = new double[size];
	}

	/**
	 * Calculate the Precision of the classifiers using the confusion matrix.
	 */
	@Override
	public void Calculation(int size, int[][] matrix) {

		double num = 0;
		for (int k = 0; k < size; k++) {
			num = matrix[k][k];
			try {
				if (matrix[k][size] == 0) throw new DivisionByZero();
				precision[k] = num / matrix[k][size] * 100;
				
			} catch( DivisionByZero e){
				precision[k] = 0;
				System.out.println("ERROR - Division by zero while calculating Precision");
				
			}
			
			precision_wa += precision[k] * matrix[size][k];
		}
		
		try {
			if (matrix[size][size] == 0) throw new DivisionByZero();
			precision_wa = precision_wa / matrix[size][size];
					
		} catch( DivisionByZero e){
			precision_wa = 0;
			System.out.println("ERROR - Division by zero while calculating Weighted Average of Precision");
			
		}

	}

	/**
	 * Calculate the Precision of the classifiers using the confusion matrix.
	 */
	@Override
	public String toString() {
		DecimalFormat df2 = new DecimalFormat("0.00");

		String sens = "Precision [";

		for (int i = 0; i < this.precision.length; i++) {
			sens += i + ":" + df2.format(precision[i]) + "% , ";
		}
		sens += df2.format(precision_wa) + "%]";

		return sens;
	}

}
