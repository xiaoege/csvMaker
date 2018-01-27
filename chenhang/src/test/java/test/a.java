package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class a {
	public static void main(String[] args) {
		List<List<Object>> listttt  =   new  ArrayList<>(); 
		List<Object> list  =   new  ArrayList<Object>();
		for(int i=0; i<5; i++){
			list.add("1.5T 两驱悦享版a"+i);
			list.add("1.5T 两驱悦享版"+i);
			listttt.add(list);
		}
		
		System.out.println(listttt);
//		for(int i=0; i<listttt.size()-1; i++){
//    		for(int j=1; j<listttt.size(); j++){
//    			if(listttt.get(i).get(1)==listttt.get(j).get(1)){
//    				listttt.remove(j);
//    			}
//    		}
//    	}
		for ( int i = 0 ; i < listttt.size() - 1 ; i ++ ) { 
			for ( int j = listttt.size() - 1 ; j > i; j -- ) { 
    			if (listttt.get(i).get(1).equals(listttt.get(j).get(1))) { 
    				listttt.remove(j); 
			} 
		} 
		System.out.println(listttt.get(10));
	}
}
}
