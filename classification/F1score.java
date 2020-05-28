package classification;

import java.text.DecimalFormat;

import network_constructor.DivisionByZero;

/**
 * F1score is a class that implements the abstract class
 * Performance_classifiers.
 */
public class F1score implements Performance_classifiers {

	/**
	 * F1score is a double vector where is stored the specificity for each class
	 */
	private double[] f1_score;
	/**
	 * F1score is a double where is stored the specificity weighted average
	 * of the classes
	 */
	private double f1_score_wa;

	/**
	 * F1score constructor.
	 * @param size Size of the F1score vector
	 */
	public F1score(int size) {
		this.f1_score = new double[size];
	}

	/**
	 *  Calculate the F1score of the classifiers using the confusion matrix.
	 */
	@Override
	public void Calculation(int size, int[][] matrix) {
		for (int i = 0; i < size; i++) {
			double den = 2 * matrix[i][i];
			double num = 2 * matrix[i][i];
			for (int k = 0; k < size; k++) {

				for (int h = 0; h < size; h++) {
					if (k == h)
						continue;
					den += matrix[h][k];
				}
			}
			try {
				
				if (den == 0) throw new DivisionByZero();
				f1_score[i] = num / den * 100;
				

			} catch( DivisionByZero e){
				f1_score[i] = 0;
				System.out.println("ERROR - Division by zero while calculating F1-Score");
				
			}
			
			f1_score_wa += f1_score[i] * matrix[size][i];
			
		}
		try {
			
			if (matrix[size][size] == 0) throw new DivisionByZero();
			f1_score_wa = f1_score_wa / matrix[size][size];
			
		} catch( DivisionByZero e){
			f1_score_wa = 0;
			System.out.println("ERROR - Division by zero while calculating Weighted Average of F1-Score");
			
		}
		
	}

	/**
	 *  The method toString() used to print the accuracy score.
	 */
	@Override
	public String toString() {
		DecimalFormat df2 = new DecimalFormat("0.00");

		String f1 = "F1score [";

		for (int i = 0; i < f1_score.length; i++) {
			f1 += i + ":" + (df2.format(f1_score[i]) + "% , ");
		}
		f1 += (df2.format(f1_score_wa) + "%], ");
		return f1;
	}

}
