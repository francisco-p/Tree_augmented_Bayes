package main;

import java.io.FileNotFoundException;

import classification.Theta_values;
import network_constructor.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException
	{
		
		// Test if the number of arguments is valid
		if (args.length != 3) {
			System.out.println("Missing arguments...");
			System.exit(1);
		}
		
		Train_action train_data = new Train_action(args[0]);
		train_data.fillvector();
				
		train_data.getTrain_graph().initedges();			
		int file_flag = train_data.read_file(train_data.getTrain_graph(),train_data);	
		if (file_flag == -1) return;
		train_data.getTrain_graph().init_counter();
		

		//Read the third argument, the test data
	    String scoring_criterion = new String(args[2]);

	    if (scoring_criterion.equals("LL") != true && scoring_criterion.equals("MDL") != true ) {
	    	System.out.println("Third argument not valid.");
			System.exit(1);
	    }
	    if (scoring_criterion.equals("LL")) {
	    	LL model = new LL();
	    	model.Set_score(train_data.getTrain_graph());
	    }
	    else {
	    	MDL model = new MDL();
	    	model.Set_score(train_data.getTrain_graph());
	    }

	    Clock time_build = new Clock();
	    
	    Algorithm tree = new Prim(); 	    
	    tree.setGraph(train_data.getTrain_graph());
	    tree.run();	     
	    
	    TAN tan = new TAN();	    
	    tan.setGraph(train_data.getTrain_graph());
	    tan.Network_construct();
	    
	    time_build.finish();
	    

	    Clock time_test = new Clock();
	    
	    Test_action test_data = new Test_action(args[1]);
	    Theta_values.setPseudoN(0.5);
	    file_flag = test_data.read_file(train_data.getTrain_graph(), train_data);
	    if (file_flag == -1) return;
	    time_test.finish();
	    test_data.setPerformance(train_data.getTrain_graph().getS());

	    
	    System.out.print(train_data.getTrain_graph().toString());
	    System.out.print(time_build.toString());	    
	    System.out.print(test_data.toString());	    
	    System.out.print(time_test.toString());    
	    test_data.getPerf().fill_matrix(test_data.getTrue_val(), test_data.getClass_test());
        test_data.getPerf().resume();
        System.out.println(test_data.getPerf().toString());
        
        
  }
}