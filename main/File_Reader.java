package main;

import java.io.FileReader;
//import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import graph.DataGraph;

/**
 * This is an abstract class that read the files ( train and test data) and
 * accordingly to which file is reading executes a different action.
 */
public abstract class File_Reader {

	/**
	 * String where the file name or path is save to be accessed
	 */
	String file;
	
	/**
	 * Number of Features in the file
	 */
	int NumberOfFeatures;
	
	/**
	 * Name of each class
	 */
	String[] Names;


	/**
	 * File_reader constructor. 
	 * @param file Name of the file to be read
	 */
	public File_Reader(String file) {
		this.file = file;
		this.NumberOfFeatures = -1;
	}

	/**
	 * read_file is the method that executes an action using this file 
	 * @param graph Graph that will be built during the reading. 
	 * @param train_data File that contains train data
	 * @return flag that is set to -1 when test file does not have the same number of features as the train data
	 */
	public int read_file(DataGraph graph, File_Reader train_data) 
	{
		String[] InArray; 
		Scanner scanIn;
		String InputLine;
		try {
			scanIn = new Scanner(new FileReader(this.file));
			InputLine = scanIn.nextLine();
			InArray = InputLine.split(",");
		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
		
		int trainSize = train_data.getNumberOfFeatures();

		try {		
			if (trainSize != -1 && InArray.length != trainSize) throw new WrongSizeLine();
			this.NumberOfFeatures = InArray.length;
			if (trainSize != -1 && Arrays.equals(train_data.getNames(), InArray) == false) throw new WrongFeaturesNames();
		} catch(WrongSizeLine e) {
			System.out.println("ERROR: Test file has the wrong number of features!");
			scanIn.close();
			return -1;
		} catch(WrongFeaturesNames e) {
			System.out.println("WARNING: Test file has Variable with different name from Train file!");
			//scanIn.close();
		}
			
			
		
		trainSize = train_data.getNumberOfFeatures();

		while (scanIn.hasNextLine()) {
			try {
				InputLine = scanIn.nextLine();
				InArray = InputLine.split(",");
				if(InArray.length != trainSize) throw new WrongSizeLine();
				fileaction(InArray, graph);
				
			} catch(WrongSizeLine e) 
			{
				System.out.println("WARNING: The file " + this.file + " has a line with the incorrect number of values");
			}
			
			
			

		}
		scanIn.close();

		
		return 0;
	}
	

	/**
	 * Method that gets the file features names
	 * @return Names of the features
	 */
	public String[] getNames() {
		return Names;
	}

	/**
	 * method that returns the number of Features
	 * @return Number of Features
	 */
	public int getNumberOfFeatures() {
		return NumberOfFeatures;
	}

	/**
	 * fileaction is a method that is called during the method read_file that
	 * implements train actions or test actions.
	 * @param InArray A String array with data read from the file
	 * @param grafo Graph that will be built during the reading. 
	 */
	protected abstract void fileaction(String[] InArray, DataGraph grafo);
	
	

}
