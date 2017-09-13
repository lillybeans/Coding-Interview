import java.util.*;

public class PaginateListings{
	  public static String[] paginate(String[] listings, int k){
	    
	  //listing: score, host_id, field1, field2, field3...
	  
	  HashSet k_set = new HashSet<Integer>(); //k unique host_ids
	  int k_index=0; //keeps track of listings per page
	  
	  for(int i=0; i<listings.length; i++){
	    if(k_index==0){ //new page
	      k_set.clear();
	    }
	    
	    String[] line = listings[i].split(",");
	    int host_id = Integer.parseInt(line[1]);
	    
	    if(!k_set.contains(host_id)){
	      k_set.add(host_id);
	    }else{ //need to swap the next distinct host_id into current position
	
	      int j=i+1; //j = next listing with distinct host id
	      while(j<listings.length && k_set.contains(Integer.parseInt(listings[j].split(",")[1]))){
	        String temp=listings[i];
	        listings[i]=listings[j];
	        listings[j]=temp;
	        j++;
	      }
	      
	      if(j != listings.length){ //did not exhaust list, successfully found next distinct host_id
	        String temp=listings[i];
	        listings[i]=listings[j];
	        listings[j]=temp;
	      } //else I don't need to do anything
	      
	    }
	    
	    k_index++;
	  }
	  
	 return listings;
	    
	}
}