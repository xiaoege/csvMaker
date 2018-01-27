package chenhang;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testString {
	
	public static void main(String[] args) {
		String str="abcdef";  
		str.substring(str.indexOf("a"),str.indexOf("c"));
		System.out.println(str.indexOf("c"));
		Map map = new HashMap<>();
		
		int [] ii = {4,2,1,6};
		Arrays.sort(ii);
		for(int i : ii){
			System.out.print(i+"\t");
		}
			
		String[] atp = {"Rafael Nadal", "Novak Djokovic",
			       "Stanislas Wawrinka",
			       "David Ferrer","Roger Federer",
			       "Andy Murray","Tomas Berdych",
			       "Juan Martin Del Potro"};
		List<String> players =  Arrays.asList(atp);
		System.out.println();
		System.out.println("--------------");
//		System.out.println(Arrays.asList(atp));
		
		// 以前的循环方式
		for (String player : players) {
		     System.out.print(player + "; ");
		}
		System.out.println();
		System.out.println("--------------");
		// 使用 lambda 表达式以及函数操作(functional operation)
		players.forEach((player) -> System.out.print(player + "; "));
		System.out.println();
		System.out.println("--------------");
		// 在 Java 8 中使用双冒号操作符(double colon operator)
		players.forEach(System.out::println);	
		
	}
	
}
	
