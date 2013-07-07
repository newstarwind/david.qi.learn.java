package david.qi.learn.java.effectivejava.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Set <--> Collection <--> List
 * Collection <--> Array
 * Collection.values()
 * Collection.toArray(new Array[0])
 * Arrays.asList()
 */
public class CollectionTransfer {  

    public static void main(String[] args) {  
        //List-->Array  
    	List<String> list = new ArrayList<String>();
    	list.add("A");
    	list.add("B");
    	list.add("C");
    	String[] array = list.toArray(new String[0]);
    	System.out.println(array);
        
        //Array-->List  
    	List<String> list2 = Arrays.asList(array);
    	System.out.println(list2);
    	
        //List-->Set  
    	Set<String> set = new HashSet<String>(list2);
    	System.out.println(set);
    	
        //Set-->List  
        List<String> list3 = new ArrayList<String>(set);
        System.out.println(list3);
    	
        //Array-->Set  
        Set<String> set2 = new HashSet<String>(Arrays.asList(array));
        System.out.println(set2);
        
        //Set-->Array  
        Set<String> set3 = new HashSet<String>(Arrays.asList("D", "E", "F"));
        String[] strArray = set3.toArray(new String[0]);
        System.out.println(strArray);
        
        //Map  
        Map<String, String> map = new HashMap<String, String>();  
        map.put("A", "1");  
        map.put("B", "2");  
        // Key to Set    
        Set<String> keySet = map.keySet();  
        System.out.println(keySet);
        // Value to Set    
        Set<String> valuesSet = new HashSet<String>(map.values());  
        System.out.println(valuesSet);
        // Value to List    
        List<String> valuesList = new ArrayList<String>(map.values());
        System.out.println(valuesList);
    }  
}  