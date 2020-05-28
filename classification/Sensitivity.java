package classification;

import java.text.DecimalFormat;

import network_constructor.DivisionByZero;

/**
 * Sensitivity is a class that implements the abstract class
 * Performance_classifiers.
 */
public class Sensitivity implements Performance_classifiers {

	/**
	 * Value of the Sensitivity in each class
	 */
	private double[] Sensitivity;
	/**
	 * Sensitivity is a double where is stored the specificity weighted average of the
	 * classes
	 */
	private double Sensitivity_wa;

	/**
	 * Sensitivity constructor
	 * 
	 * @param size Size of the Sensitivity vector
	 */
	public Sensitivity(int size) {
		Sensitivity = new double[size];
	}

	/**
	 * Calculate the specificity of the classifiers using the confusion matrix.
	 */
	@Override
	public void Calculation(int size, int[][] matrix) {

		double num = 0;
		for (int k = 0; k < size; k++) {
			num = matrix[k][k];
			try {
				if (matrix[k][size] == 0) throw new DivisionByZero();
				Sensitivity[k] = num / matrix[size][k] * 100;
				
			} catch( DivisionByZero e){
				Sensitivity[k] = 0;
				System.out.println("ERROR - Division by zero while calculating Sensitivity");
				
			}
			
			Sensitivity_wa += Sensitivity[k] * matrix[size][k];
		}
		
		try {
			if (matrix[size][size] == 0) throw new DivisionByZero();
			Sensitivity_wa = Sensitivity_wa / matrix[size][size];
					
		} catch( DivisionByZero e){
			Sensitivity_wa = 0;
			System.out.println("ERROR - Division by zero while calculating Weighted Average of Sensitivity");
			
		}

			
	}

	/**
	 * Calculate the Sensitivity of the classifiers using the confusion matrix.
	 */
	@Override
	public String toString() {
		DecimalFormat df2 = new DecimalFormat("0.00");
		String sens = "Sens [";

		for (int i = 0; i < this.Sensitivity.length; i++) {
			sens += i + ":" + df2.format(Sensitivity[i]) + "% , ";
		}
		sens += df2.format(Sensitivity_wa) + "%], ";

		return sens;
	}

}
