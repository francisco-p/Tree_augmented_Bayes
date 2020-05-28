package network_constructor;


/**
 * Class that builds an exception whenever there is 
 * a division by zero.
 */
public class LogarithmOfZero extends Exception {

	private static final long serialVersionUID = 1L; 
	
	/**
	 * Builds a new Exception without detailed message.
	 */
	public LogarithmOfZero() {
		super("Logarythm of zero");
			
	}
	
	/**
	 * Builds a new Exception with this detailed message.
	 * @param message corresponds to the detailed message.
	 */
	public LogarithmOfZero(String message) {
		         super(message);
	}
	

    
}