package main;

/**
 * This is a class that can be used to measure a time interval 
 */
public class Clock {
	
	/**
	 * long object where the start of the time is stored
	 */
	private long startTime;
	/**
	 * long object where the end of the time is stored
	 */
	private long endTime;
	
	/**
	 * Clock constructor the automatically starts time
	 */
	public Clock() {
		this.startTime = System.currentTimeMillis();
	}
	
	/**
	 * finish is the method that sets the time interval
	 */
	public void finish() {
		endTime = System.currentTimeMillis();
		this.endTime  = endTime - this.startTime;
		
	}


	/**
	 * toString is the method that returns the time interval
	 */
	@Override
	public String toString() {
		return "\nTime:		"+ this.endTime + " milliseconds\n";
	}



}
