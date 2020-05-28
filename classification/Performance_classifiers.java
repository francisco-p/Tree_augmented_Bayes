package classification;
/**
 * This interface is used with the main objective of implementing 
 * different performance classifiers, they are implemented 
 * using the methods in this interface.
 *
 */
public interface Performance_classifiers {
	
	/**
	 * Calculation is a method which will be overridden in 
	 * order to implement the different performance classifiers 
	 * @param size The size of the confusion matrix.
	 * @param matrix - Confusion Matrix
	 */
	public void Calculation(int size, int [][] matrix);	
	
	/**
	 * Method used to print this performance classifiers
	 * @return A String to be printed
	 */
	public abstract String toString();

}
