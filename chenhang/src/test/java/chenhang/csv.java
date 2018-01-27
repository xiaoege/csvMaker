package chenhang;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class csv {
	public static void main(String[] args) {
		createCSV();
	}
	
	public static void createCSV() {
		        
		     // 表格头
		     Object[] head = { "客户姓名", "证件类型", "日期", };
//		     Object[] head = { "配件名称", "品牌车型", "配件型号", "4S店价格（元）","正厂件价格（元）","市场价格（元）",};
		     List<Object> headList = Arrays.asList(head);
		
		     //数据
		     List<List<Object>> dataList = new ArrayList<List<Object>>();
		     List<Object> rowList = null;
		     for (int i = 0; i < 100; i++) {
		    	 rowList = new ArrayList<Object>();
		    	 rowList.add("张三" + i);
		    	 rowList.add("263834194" + i);
		    	 rowList.add(new Date());
		    	 dataList.add(rowList);
		     }
		          
		     String fileName = "zhangsan.csv";//文件名称
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
		
		        // GB2312使正确读取分隔符","
	            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
	            int num = headList.size() / 2;
	            StringBuffer buffer = new StringBuffer();
	            for (int i = 0; i < num; i++) {
	                buffer.append(" ,");
	             }
	            csvWtriter.write(buffer.toString() + fileName + buffer.toString());
	            csvWtriter.newLine();
		
	            // 写入文件头部
	            writeRow(headList, csvWtriter);
		 
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
