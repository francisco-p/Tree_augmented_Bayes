package main;


/**
 * Class that builds an exception whenever there is 
 * a file line of the wrong size
 */
public class WrongSizeLine extends Exception {

	private static final long serialVersionUID = 1L; 
	
	/**
	 * Builds a new Exception without detailed message.
	 */
	public WrongSizeLine() {
		super("Division by zero");
			
	}
	
	/**
	 * Builds a new Exception with this detailed message.
	 * @param message corresponds to the detailed message.
	 */
	public WrongSizeLine(String message) {
		         super(message);
	}
	

    
}
