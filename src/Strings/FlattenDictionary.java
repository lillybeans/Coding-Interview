package Strings;

import java.util.*;

class FlattenDictionary {
	
  static HashMap<String, String> flattenDictionary(HashMap<String, Object> dict) {
    // your code goes here
    //go through the hashmap, append key of object to current key, until object is a string, return
    
    HashMap<String, String> answer=new HashMap<String, String>();
    
    if(dict.isEmpty()){
      return new HashMap<String, String>();
    }
    
    flattenDictionaryHelper("", dict, answer);
     return answer;
 
    }
    
    static void flattenDictionaryHelper(String prefix, HashMap<String, Object> child, HashMap<String, String> answer){
      for(Map.Entry<String, Object> entry: child.entrySet()){
        String key=entry.getKey();
        if(entry.getValue() instanceof String){
          if(prefix==""){
            answer.put(key,(String)entry.getValue());
          }else{
          answer.put(prefix+"."+key,(String)entry.getValue());
          }
         }else{
           flattenDictionaryHelper(prefix+"."+key,(HashMap)entry.getValue(),answer);
       }
      }
       return;
  }
 

  public static void main(String[] args) {
	  HashMap<String, Object> map=new HashMap<String, Object>();
	  
  }

}
