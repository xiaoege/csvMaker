package chenhang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test0 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		String s1 = "s1";
		String s2 = "s2";
		String s3 = "s3";
		list.add(s1);
		list.add(s2);
		list.add(s3);
		/*Map<String,Object> map = new HashMap<>();
		map.put("s1", s1);
		map.put("s2", s2);
		map.put("s3", s3);
		map.put("a1", "a1");
		map.put("a2", "a2");
		map.put("a3", "a3");
//		System.out.println(map);
		map.put("list", list);
		System.out.println(map);*/
		
		System.out.println(list);
		String a = "F:\\mywork2\\beijing\\countyId0_brandId82_seriesId3574_factoryId0_pageIndex1_kindId0_orderType0_isSales0";
		String b = "F:\\mywork2\\beijing\\countyId0_brandId82_seriesId3574_factoryId0_pageIndex1_kindId0_orderType0_isSales0";
		System.out.println(a.equals(b));
	}
}
