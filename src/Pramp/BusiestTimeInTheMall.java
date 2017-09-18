package Pramp;
import java.io.*;
import java.util.*;

class  BusiestTimeInTheMall { //find the time where the mall has the maximum number of people
	
  static int findBusiestPeriod(int[][] data) {
    int net=0;
    int peak=0;
    
    if(data[0][2] == 1){
      net+=data[0][1];
    }else{
      net-=data[0][1];
    }
    
    int peak_timestamp=data[0][0]; //initialize to be the first timestamp
    
    for(int i=1; i<data.length; i++){
      int timestamp = data[i][0];
      int visitors = data[i][1];
      int enter = data[i][2];
      
      int prev_timestamp = data[i-1][0];
      if(timestamp != prev_timestamp){ //not same as last timestamp, check previous net before updating net
         if(net > peak){ //check previous net
          peak=net;
          peak_timestamp=prev_timestamp;
        }
      }
      
      //update number of visitors so far
      if(enter == 1){ //enter
        net += visitors; 
      }else{ //exit
        net -= visitors;
      }
     
      //special case for last timestamp, must recalculate total after the update to see if this is the new peak
      if(timestamp == prev_timestamp && i==data.length-1){
        if(net > peak){
          peak=net;
          peak_timestamp = timestamp;
        }
      }
    }
    
    return peak_timestamp;
  }

  public static void main(String[] args) {
	  
	  //data[i][0]=timestamp, data[i][1]=number of visitors, data[i][0]=1 for entrance, 0 for exit
	  
	  int[][] data= {{1487799425,14,1},
			  		 {1487799425,4,0},
			  		 {1487799425,2,0},
			  		 {1487800378,10,1},
			  		 {1487801478,18,0},
			  		 {1487801478,19,1},
			  		 {1487801478,1,0},
			  		 {1487801478,1,1},
			  		 {1487901013,1,0},
			  		 {1487901211,7,1},
			  		 {1487901211,8,0}};
	  
	  System.out.println(findBusiestPeriod(data)); //prints timestamp at peak
	  

  }

}