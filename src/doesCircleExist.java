//Robot commands:
//G - go forward 1 step
//L - turn left
//R - turn right

//given a string command, i.e. GLGR, find if a circle of finite radius exists such that if the command were repeated infinitely, the robot's trajectory would be bound within that circle

public class doesCircleExist {
	/*
	 * Complete the function below.
	 */

	//The only time the circle does not exist is when you end up in a different orientation than you've started. Else, you will either end up where you started after 2 executions or 4 executions.
	    static String[] doesCircleExist(String[] commands) {
	        
	        String[] result = new String[commands.length];
	        
	        for (int i=0; i<commands.length; i++){
	            
	            int nL = 0;
	            int nR = 0;

	            char[] chars = commands[i].toCharArray();

	            for (char c: chars){
	                if (c == 'L'){
	                    nL++;
	                    }
	                    else if (c == 'R'){
	                        nR++;
	                    }
	            }
	            
	             if ( (nL == 0 && nR % 4 == 0) || ( nR == 0 && nL % 4 == 0)){
	                   result[i]="NO";
	            }
	            else if (nR % 4 == nL % 4){
	                   result[i]="NO";
	             }
	             else{
	                  result[i]="YES";
	             }
	        }
	                    
	        return result;

	    }


}
