package classification;

import java.text.DecimalFormat;

import network_constructor.DivisionByZero;

/**
 * Accuracy is a class that implements the abstract class 
 * Performance_classifiers.
 */
public class Accuracy implements Performance_classifiers {

	/**
	 * Value of the accuracy
	 */
	private double accuracy;

	/**
	 * Calculate the accuracy of the classifiers using
	 * the confusion matrix.
	 */
	@Override
	public void Calculation(int size, int[][] matrix) {

		double num = 0;
		for (int k = 0; k < size; k++) {
			num += matrix[k][k];
		}
		try {
			if (matrix[size][size] == 0) throw new DivisionByZero(); 
			accuracy = num / (double) matrix[size][size] * 100;
		} catch( DivisionByZero e){
			accuracy = 0;
			System.out.println("ERROR - Division by zero while calculating Accuracy");	
		}

	}

	/**
	 * The method toString() used to print the accuracy score.
	 */
	@Override
	public String toString() {
		DecimalFormat df2 = new DecimalFormat("0.00");
		return  "\nResume:			Accuracy " + df2.format(accuracy) + "% , ";
	}




	
}
