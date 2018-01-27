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


public class csvLoaderType2 {
	public static void main(String[] args) throws IOException {
		String path="E:/souhu_car";
		//输入文件夹地址,文件夹内部不能有文件夹,只能全是HTML文件   汽车保养
		path = "C:/Users/Administrator/Desktop/搜狐汽车-保养/shanghaivw_2251_上汽大众_朗逸.html";
		path = "C:/Users/Administrator/Desktop/搜狐汽车-保养";
//		path = "C:/Users/Administrator/Desktop/error";
		makeCsvFileFromFolder(path);
	}

	public static void makeCsvFileFromFolder(String path) throws FileNotFoundException, IOException {
		File file=new File(path);
		File[] tempList = file.listFiles();
		for (int filenum = 0; filenum < tempList.length; filenum++) {
			if (tempList[filenum].isFile() && !tempList[filenum].getName().contains("疑似无保养信息")) {
			    FileInputStream in=new FileInputStream(tempList[filenum]);
		        // size  为字串的长度 ，这里一次性读完
		        int size=in.available();
		        byte[] buffer=new byte[size];
		        in.read(buffer);
		        in.close();
		        String sb = new String(buffer,"UTF-8").replace("\t", "").replace("\r", "").replace("\n", "");
		        
		        int iName = sb.indexOf("xcpp");
		        int iNameEnd = sb.indexOf("</a>",iName);
		        String tittle = sb.substring(iName+6,iNameEnd);  //品牌 例如: 上汽通用
		        
		        int iType = sb.indexOf("xccx");
		        int iTypeEnd = sb.indexOf("</a>",iType);
		        String type = sb.substring(iType+6,iTypeEnd); //车系  例如: 五菱宏光
		        
		        int sel_con = sb.indexOf("sel_con",sb.indexOf("sel_con")+1);  //车型  ：五菱宏光S
		        int sel_con_ul = sb.indexOf("<ul>", sel_con);
		        int sel_conEnd = sb.indexOf("</ul>",sel_con);
		        String carType = sb.substring(sel_con_ul+4,sel_conEnd).replace("\"", "").trim();
		        String [] carTypeArray = carType.split("</li>");
		        int ccc=0;
		        //缩小数据范围
		        sb = sb.substring(sb.indexOf("list2"),sb.indexOf("special")-24); 
		        sb = sb.substring(sb.indexOf("<div"),sb.lastIndexOf("</table>"));
//		        System.out.println(sb);
		        String [] groupData = sb.split("</div>");  //一种车型的保养指数
		        List<List<Object>> list = new ArrayList<List<Object>>();
				List<Object> list2 = null;
		        for(String s : groupData){
		        	
		        	int s1 = s.indexOf("<tbody>")+7;
		        	int s2 = s.indexOf("</tbody>");
		        	String baoyang = s.substring(s1, s2).replace("<tr>", "").replace("</tr>", "").replace("<td>", "");
		        	
		        	int s3 = s.indexOf("<tbody>",s2);
		        	String peijian = s.substring(s3+7,s.lastIndexOf("</tbody>")).replace("<tr>", "").replace("</tr>", "").replace("<td>", "");
		        	
		        	//保养信息
		        	String [] result =  baoyang.replace("\"", "").replace(" class=bg>", "").split("<td");
		        	for(int i=1; i<result.length; i++){
		        		list2 = new ArrayList<>(); 
		        		list2.add(tittle);
			        	list2.add(type);
		        		list2.add(carTypeArray[ccc].replaceAll("^<li.*?>", ""));
			        	
		        		String [] result2 = result[i].split("</td>");
		        		for(int k=0; k<result2.length; k++){
		        			list2.add(result2[k]);
		        		}
		        		list.add(list2);
		        	}
		        	//保养费用
		        	/*String [] resultPeijian = peijian.replace("&nbsp;", "").replace("\"", "").replace(" class=bg>", "").split("<td");
		        	for(int i=1; i<resultPeijian.length; i++){
		        		list2 = new ArrayList<>(); 
		        		list2.add(tittle);
			        	list2.add(type);
		        		list2.add(carTypeArray[ccc].replaceAll("^<li.*?>", ""));
			        	
		        		String [] result2 = resultPeijian[i].split("</td>");
		        		for(int k=0; k<result2.length; k++){
		        			list2.add(result2[k]);
		        		}
		        		list.add(list2);
		        	}*/
		        	ccc++;
		        }
		        	
	        	/*for ( int i = 0 ; i < list.size() - 1 ; i ++ ) { 
	    			for ( int j = list.size() - 1 ; j > i; j -- ) {
	    				List<Object> a = list.get(i);
	    				List<Object> b =list.get(j);
	    				String a1=(String) a.get(2);
	    				String b1=(String) b.get(2);
	        			if (a1.equals(b1)) { 
	        				list.remove(j);
	        				System.out.println("刪除重复数据");
	        			} 
	    			}
	        	}*/
	        	createCsvFile(list,tempList[filenum].getName());
			}
		}
	}
	
	public static void createCsvFile(List<List<Object>> dataList,String name) {
	     // 表格头
	     Object[] head = { "配件名称", "品牌车型", "配件型号", "4S店价格（元）","正厂件价格（元）","市场价格（元）",};
	     List<Object> headList = Arrays.asList(head);
	
	     String fileName = "a1.csv";//文件名称
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
