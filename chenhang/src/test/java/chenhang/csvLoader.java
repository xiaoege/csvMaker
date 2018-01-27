package chenhang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class csvLoader {
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("C:/Users/Administrator/Desktop/quote/quote/baoma-5xiE3995_03-1.html");
		BufferedReader br = new BufferedReader(fr);
		
		String lineString ;
		StringBuffer sb = new StringBuffer();
		while((lineString = br.readLine()) != null ){
				sb.append(lineString);
		}
		br.close();
		fr.close();
		
		int index = sb.indexOf("<table");
		int lastIndex = sb.lastIndexOf("</table");
		String result = sb.substring(index, lastIndex);
		String td;
		//第1个td标签的长度
		int tdLength = result.substring(result.indexOf("<td>"),result.indexOf("</td>")+1).length();
		Map<String,Object> map = new HashMap<>();
			for (int i = 0; i < result.length(); i++) {
				/*int beginIndex = result.indexOf("<td>", i);
				int endIndex = result.indexOf("<td>",++i);
				td = result.substring(beginIndex, endIndex);*/
				map.put("td", "td");
			}
		System.out.println(result.indexOf("<td>",result.indexOf("<td>")+3));
		
	}
}
