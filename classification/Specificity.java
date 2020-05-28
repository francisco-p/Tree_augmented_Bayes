package classification;

import java.text.DecimalFormat;

import network_constructor.DivisionByZero;

/**
 * Specificity is a class that implements the abstract class
 * Performance_classifiers.
 *
 */
public class Specificity implements Performance_classifiers {

	/**
	 * Specificity is a double vector where is stored the specificity for each class
	 */
	private double[] specificity;

	/**
	 * specificity_wa is a double where is stored the specificity weighted average
	 * of the classes
	 */
	private double specificity_wa;

	/**
	 * Specificity constructor.
	 * @param size Size of the specificity vector
	 */
	public Specificity(int size) {
		this.specificity = new double[size];
	}

	/**
	 * Calculate the specificity of the classifiers using the confusion matrix.
	 */
	@Override
	public void Calculation(int size, int[][] matrix) {

		for (int i = 0; i < size; i++) {
			double den = 0;
			double num = 0;
			for (int k = 0; k < size; k++) {
				for (int h = 0; h < size; h++) {
					if (k == i || h == i)
						continue;
					num += matrix[k][h];
				}
				if (k != i)
					den += matrix[size][k];
			}
			try {
				if ( den == 0) throw new DivisionByZero();
				specificity[i] = num / den * 100;
			}
			catch(DivisionByZero e) {
				specificity[i] = 0;
				System.out.println("ERROR - Division by zero while calculating Specifitity");
				
			}
			specificity_wa += specificity[i] * matrix[size][i];
		}
		try {
			if (matrix[size][size] == 0) throw new DivisionByZero();
			specificity_wa = specificity_wa / matrix[size][size];
					
		} catch( DivisionByZero e){
			specificity_wa = 0;
			System.out.println("ERROR - Division by zero while calculating Weighted Average of Specificity");
			
		}

	}

	/**
	 * The method toString() used to print the accuracy score.
	 */
	@Override
	public String toString() {

		DecimalFormat df2 = new DecimalFormat("0.00");
		String spec = "Spec [";
		for (int i = 0; i < specificity.length; i++) {
			spec += (i + ":" + df2.format(this.specificity[i]) + "% , ");
		}
		spec += df2.format(this.specificity_wa) + "%], ";

		return spec;
	}

}
