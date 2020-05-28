package main;


/**
 * Class that builds an exception whenever there is 
 * a wrong feature name in the test file
 */
public class WrongFeaturesNames extends Exception {

	private static final long serialVersionUID = 1L; 
	
	/**
	 * Builds a new Exception without detailed message.
	 */
	public WrongFeaturesNames() {
		super("Division by zero");
			
	}
	
	/**
	 * Builds a new Exception with this detailed message.
	 * @param message corresponds to the detailed message.
	 */
	public WrongFeaturesNames(String message) {
		         super(message);
	}
	

    
}
