package network_constructor;


/**
 * Class that builds an exception whenever there is 
 * a division by zero.
 */
public class DivisionByZero extends Exception {

	private static final long serialVersionUID = 1L; 
	
	/**
	 * Builds a new Exception without detailed message.
	 */
	public DivisionByZero() {
		super("Division by zero");
			
	}
	
	/**
	 * Builds a new Exception with this detailed message.
	 * @param message corresponds to the detailed message.
	 */
	public DivisionByZero(String message) {
		         super(message);
	}
	

    
}
