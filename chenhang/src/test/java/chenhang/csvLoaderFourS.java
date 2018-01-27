package chenhang;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class csvLoaderFourS {

	List<String> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		String path = "F:\\mywork2\\beijing\\countyId110106_brandId58_seriesId0_factoryId0_pageIndex1_kindId0_orderType0_isSales0 ";
		path = "F:\\mywork2\\beijing\\countyId110106_brandId58_seriesId0_factoryId0_pageIndex1_kindId0_orderType0_isSales0";
		
//		find(path);

		find("F:\\mytest\\chen");
	}
	
	public static void find(String pathName) throws IOException {
		// 获取pathName的File对象
		File dirFile = new File(pathName);
		String[] fileList = dirFile.list();
		if(null != fileList){
			for (int i = 0; i < fileList.length; i++) {
				// 遍历文件目录
				String string = fileList[i];
				// File("documentName","fileName")是File的另一个构造器
				File file = new File(dirFile.getPath(), string);
				String name = file.getCanonicalPath();
				// 如果是一个目录，搜索深度depth++，输出目录名后，进行递归
				if (file.isDirectory()) {
					// 递归
					find(file.getCanonicalPath());
				} else {
					// System.out.println(name);
					FileInputStream in = new FileInputStream(name);
					// size 为字串的长度 ，这里一次性读完
					int size = in.available();
					byte[] buffer = new byte[size];
					in.read(buffer);
					in.close();
					String sb = new String(buffer, "UTF-8");
					Document doc = Jsoup.parse(sb);
					Elements elementsByClass = doc.getElementsByClass("info-wrap");
					if (elementsByClass.size() > 0) {
						Object[] array = elementsByClass.toArray();
						List<List<Object>> resultList = new ArrayList<List<Object>>();
						List<Object> list = null;
						for (int ii = 0; ii < array.length; ii++) {
							list = new ArrayList<>();
							String[] result = array[ii].toString().replace("\"", "").split("</li>");
							String shopName = result[0].substring(result[0].indexOf("<span>") + 6,
									result[0].indexOf("</span>"));
							String card = result[0]
									.substring(result[0].lastIndexOf("<!--") + 4, result[0].lastIndexOf("-->")).trim();
							String sale = result[1].substring(result[1].indexOf("<em>") + 4, result[1].indexOf("</em>"));
							String saleNum = result[1].substring(result[1].indexOf("<a"), result[1].indexOf("</a>"))
									.replaceAll("^<a.*?>", "");
							String phone = result[2].substring(result[2].indexOf("tel>") + 4,
									result[2].indexOf("</span>", result[2].indexOf("tel")));
							String openTime = result[2];
							if(openTime.contains("gray>")){
								openTime = result[2].substring(result[2].indexOf("gray>") + 5,
										result[2].indexOf("</span>", result[2].indexOf("gray")));
							}else{
								openTime = "";
							}
							String saleScope = result[2].substring(result[2].indexOf("floating>") + 9,
									result[2].indexOf("</span>", result[2].indexOf("floating")));
							String address = result[3].substring(result[3].indexOf("info-addr>") + 10,
									result[3].indexOf("</span>", result[3].indexOf("info-addr")));
							list.add(shopName);
							list.add(card);
							list.add(sale);
							list.add(saleNum);
							list.add(phone);
							list.add(openTime);
							list.add(saleScope);
							list.add(address);
							resultList.add(list);
						}
						createCsvFile(resultList,name);
					}
				}
			}
		}
	}
	public static void createCsvFile(List<List<Object>> dataList,String name) {
	     // 表格头
	     Object[] head = { "配件名称", "品牌车型", "配件型号", "4S店价格（元）","正厂件价格（元）","市场价格（元）",};
	     List<Object> headList = Arrays.asList(head);
	
	     String fileName = "data.csv";//文件名称
	     String filePath = "E:/"; //文件路径
	        
	     File csvFile = null;
	     BufferedWriter csvWtriter = null;
	     try {
	        csvFile = new File(filePath + fileName);
           File parent = csvFile.getParentFile();
	        if (parent != null && !parent.exists()) {
	             parent.mkdirs();
	        }
	        csvFile.createNewFile();
	
	        // GB2312使正确读取分隔符","  true参数表示追加内容
          csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile,true), "GB2312"), 1024);
          int num = headList.size() / 2;
          StringBuffer buffer = new StringBuffer();
          for (int i = 0; i < num; i++) {
              buffer.append(" ,");
           }
          /*csvWtriter.write(buffer.toString() + name + buffer.toString());
          csvWtriter.newLine();*/
	
          // 写入文件头部
          
//          writeRow(headList, csvWtriter);
	 
          // 写入文件内容
          for (List<Object> row : dataList) {
               writeRow(row, csvWtriter);
           }
	        csvWtriter.flush();
	        } catch (Exception e) {
	            e.printStackTrace();
	         } finally {
	             try {
               csvWtriter.close();
	            } catch (IOException e) {
	               e.printStackTrace();
	            }
	        }
	    }
	      
	    /**
	    * 写一行数据
	    * @param row 数据列表
	    * @param csvWriter
	    * @throws IOException
	    */
	    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
	        for (Object data : row) {
	        	StringBuffer sb = new StringBuffer();
	            String rowStr = sb.append("\"").append(data).append("\",").toString();
	            csvWriter.write(rowStr);
       }
	        	csvWriter.newLine();
	    }

}