package test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class csvLoader {
	public static void main(String[] args) throws IOException {
		String path="C:/Users/Administrator/Desktop/eee/quote";
//		path="C:/Users/Administrator/Desktop/eee/quote-detail";
//		path = "C:/Users/Administrator/Desktop/eee/detail1";
//		path = "C:/Users/Administrator/Desktop/eee/detail2";
		path = "C:/Users/Administrator/Desktop/eee/detail3";
		//输入文件夹地址,文件夹内部不能有文件夹,只能全是HTML文件
//		makeCsvFileFromFolder(path);
		//detail文件夹专用
		makeCsvFileFromDetail(path);
	}

	public static void makeCsvFileFromFolder(String path) throws FileNotFoundException, IOException {
//		String path="C:/Users/chen/Desktop/quote/quote/";
		File file=new File(path);
		File[] tempList = file.listFiles();
		for (int filenum = 0; filenum < tempList.length; filenum++) {
			if (tempList[filenum].isFile()) {
		    FileInputStream in=new FileInputStream(tempList[filenum]);
	        // size  为字串的长度 ，这里一次性读完
	        int size=in.available();
	        byte[] buffer=new byte[size];
	        in.read(buffer);
	        in.close();
	        String sb = new String(buffer,"UTF-8");
	        
			int tableIndex = sb.indexOf("<table");
			int tableLastIndex = sb.indexOf("</table",tableIndex);
			String result = sb.substring(tableIndex, tableLastIndex); //需要取值的table，缩小范围
			result = result.substring(result.lastIndexOf("</th>"),result.length());
			result = result.substring(result.indexOf("<tr"),result.length());
			
			List<List<Object>> list = new ArrayList<List<Object>>();
			List<Object> list2 = null;
			String[] aaa = result.split("</tr>");
			for(int i=0 ; i<aaa.length ; i++){
				list2 = new ArrayList<>(); 
				String s = aaa[i].substring(80, aaa[i].lastIndexOf("</td>")+5).trim(); //+5拿到每行最后一个td
				s = s.replace("<td>", "").replace("\"", "").replace("</a>", "").replace("<span class=price>", "")
						.replace("</span>", "").replace(" ", "");
//				s = s.replace("-", "0");
				s = s.replaceFirst("^<a.*?html>", "");
				
				String [] ss = s.split("</td>");
				list2.add(ss[0]);
				list2.add(ss[1].trim());
				list2.add(ss[2].trim());
				list2.add(ss[3].trim().replace("-", "0"));
				list2.add(ss[4].trim().replace("-", "0"));
				list2.add(ss[5].trim().replace("-", "0"));
				list.add(list2);
			}
			
			createCsvFile(list,tempList[filenum].getName());
			
			}
		}
	}
	
	
	
	public static void makeCsvFileFromDetail(String path) throws FileNotFoundException, IOException {
		File file=new File(path);
		File[] tempList = file.listFiles();
		for (int filenum = 0; filenum < tempList.length; filenum++) {
			if (tempList[filenum].isFile()) {
				FileInputStream in=new FileInputStream(tempList[filenum]);
		        // size  为字串的长度 ，这里一次性读完
		        int size=in.available();
		        byte[] buffer=new byte[size];
		        in.read(buffer);
		        in.close();
		        String sb = new String(buffer,"UTF-8");
		        //过滤空的HTML
		        if(sb.length()>500){
					int kkk = sb.indexOf("<table")+10;
					int tableIndex = sb.indexOf("<table",kkk); //拿到detail文件夹里的文件的第2个table
					int tableLastIndex = sb.indexOf("</table",tableIndex);
					String result = sb.substring(tableIndex, tableLastIndex); //需要取值的table，缩小范围
					result = result.substring(result.lastIndexOf("</th>"),result.length());
					result = result.substring(result.indexOf("<tr"),result.length());
					
					List<List<Object>> list = new ArrayList<List<Object>>();
					List<Object> list2 = null;
					String[] aaa = result.split("</tr>");
					for(int i=0 ; i<aaa.length ; i++){
						list2 = new ArrayList<>(); 
						String s = aaa[i].substring(80, aaa[i].lastIndexOf("</td>")+5).trim(); //+5拿到每行最后一个td
						s = s.replace("<td>", "").replace("\"", "").replace("</a>", "").replace("<span class=price>", "")
								.replace("</span>", "").replace(" ", "");
//						s = s.replace("-", "0");
						s = s.replaceFirst("^<a.*?html>", "");
						
						String [] ss = s.split("</td>");
						/*for(int j=0 ; j<ss.length ; j++){
							list2.add(ss[j]);
						}*/
						list2.add(ss[0]);
						list2.add(ss[1].trim());
						list2.add(ss[2].trim());
						list2.add(ss[3].trim().replace("-", "0"));
						list2.add(ss[4].trim().replace("-", "0"));
						list2.add(ss[5].trim().replace("-", "0"));
						list.add(list2);
					}
					
					createCsvFile(list,tempList[filenum].getName());
		        }
				}
			}
		}
	
	public static void createCsvFile(List<List<Object>> dataList,String name) {
	     // 表格头
	     Object[] head = { "配件名称", "品牌车型", "配件型号", "4S店价格（元）","正厂件价格（元）","市场价格（元）",};
	     List<Object> headList = Arrays.asList(head);
	
	     String fileName = "a3.csv";//文件名称
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
           
//           writeRow(headList, csvWtriter);
	 
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
