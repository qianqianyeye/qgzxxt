package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class LogUtils {
	private static final String LOGPATH;
	private static final String SYSTEMLOG="systemlog";
	private static final String ERROR="-error";
	private static final String EVENT="-event";
	private static final String WARN="-warn";
	
	static {
		String path=LogUtils.class.getResource("/").toString();
		if (path.indexOf("webapps")!=-1) {
			LOGPATH=path.substring(6, path.indexOf("webapps"))+"logs";
		}else{
			LOGPATH=path.substring(6, path.length())+"logs";
		}
	}
	
	public static void warn(String message){
		warn(message,null);
	}
	
	public static void event(String message){
		event(message,null);
	}
	
	public static void back(String message){
		back(message, null);
	}
	
	public static void back(String message,String tenantId){
		print(message, tenantId, "back");
	}
	
	public static void warn(String message,String tenantId){
//		String fileName=DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD)+WARN+".log";
//		String filePath=null;
//		if (tenantId!=null && !tenantId.isEmpty()) {
//			filePath=LOGPATH+"/"+tenantId+"/warn/"+fileName;
//		}else{
//			filePath=LOGPATH+"/"+SYSTEMLOG+"/warn/"+fileName;
//		}
//		addLogInfo(filePath, message);
		print(message, tenantId, "warn");
	}
	
	public static void event(String message,String tenantId){
//		String fileName=DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD)+EVENT+".log";
//		String filePath=null;
//		if (tenantId!=null && !tenantId.isEmpty()) {
//			filePath=LOGPATH+"/"+tenantId+"/event/"+fileName;
//		}else{
//			filePath=LOGPATH+"/"+SYSTEMLOG+"/event/"+fileName;
//		}
//		addLogInfo(filePath, message);
		print(message, tenantId, "event");
	}
	
	private static void print(String message,String tenantId,String type){
		String fileName=DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD)+"-"+type+".log";
		String filePath=null;
		if (tenantId!=null && !tenantId.isEmpty()) {
			filePath=LOGPATH+"/"+tenantId+"/"+type+"/"+fileName;
		}else{
			filePath=LOGPATH+"/"+SYSTEMLOG+"/"+type+"/"+fileName;
		}
		message=addTimeToMessage(message);
		addLogInfo(filePath, message);
	}
	
	private static String addTimeToMessage(String message){
		if (message!=null && !message.isEmpty() ) {
			return DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HHMMSS)+"  "+message;
		}
		return "";
	}
	
	private static void addLogInfo(String fileFullName,String message){
		BufferedWriter fw = null;
		try {
			if (!FileUtils.isFileExist(fileFullName)) {
				FileUtils.makeDirectory(fileFullName);
			}
			File file = new File(fileFullName);
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); 
			//fw.append(DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HHMMSS)+"  ");
			if (message!=null && !message.isEmpty() ) {
				fw.append(message);
				fw.newLine();
			}
			fw.flush(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void error(Throwable throwable){
		error(throwable,null,null);
	}
	
	public static void error(Throwable throwable,String message){
		error(throwable,message,null);
	}
	
	public static void error(Throwable throwable,String message,String tenantId){
		BufferedWriter fw = null;
		try {
			String fileName=DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD)+ERROR+".log";
			String filePath=null;
			if (tenantId!=null && !tenantId.isEmpty()) {
				filePath=LOGPATH+"/"+tenantId+"/error/"+fileName;
			}else{
				filePath=LOGPATH+"/"+SYSTEMLOG+"/error/"+fileName;
			}
			if (!FileUtils.isFileExist(filePath)) {
				FileUtils.makeDirectory(filePath);
			}
			File file = new File(filePath);
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
			fw.append(DateUtils.getCurrentDate(DateUtils.YYYY_MM_DD_HHMMSS));
			fw.newLine();
			if (message!=null && !message.isEmpty() ) {
				fw.append(message);
				fw.newLine();
			}
			if(throwable!=null){
				StringWriter sw = new StringWriter();   
				throwable.printStackTrace(new PrintWriter(sw, true));   
		        String strs = sw.toString(); 
		        fw.append(strs);
		        fw.newLine();
			}
			fw.append("-----------------------------------------------------------------");
			fw.newLine();
			fw.flush(); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getHtmlLog(String date,String logType,String tenantId){
		String fileName=date+"-"+logType+".log";
		String filePath=null;
		if (tenantId!=null && !tenantId.isEmpty()) {
			filePath=LOGPATH+"/"+tenantId+"/"+logType+"/"+fileName;
		}else{
			filePath=LOGPATH+"/"+SYSTEMLOG+"/"+logType+"/"+fileName;
		}
		File file=new File(filePath);
		String result = "";
		if(file.isFile() && file.exists()){ 
	    	try{
	    		InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),"utf-8");//考虑到编码格式
	            BufferedReader br = new BufferedReader(read);
	            String s = null;
	            while((s = br.readLine())!=null){
	                result = result  +s+ "<br>";
	            }
	            br.close();    
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
        return result;
	}
	
	public static boolean clearLog(){
		return FileUtils.deleteDirectory(LOGPATH);
	}
	
	public static boolean clearLog(String tenantId){
		return FileUtils.deleteDirectory(LOGPATH+"/"+tenantId);
	}
	/**
	 * 删除几天提前日志
	 * @param tenantId
	 * @param type
	 * @param days
	 */
	public static void clearLog(String tenantId,String type,int days){
		String path=LOGPATH+"/"+tenantId+"/"+type;
		File file = new File(path);
        File [] files = file.listFiles();
        for (File file2 : files) {
        	if (file2.isFile()) {
    		  Calendar   cal   =   Calendar.getInstance();
      		  List<String> lists=new ArrayList<String>();
      		 cal.add(Calendar.DATE,   0);
      		 String yesterdays = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime())+"-"+type+".log";
  			 yesterdays=yesterdays.replace(" ", "");
      		 lists.add(yesterdays);
      		 if(days==0){
      			 file2.delete();
      		 }
      		 else{
      			for(int i=0;i<=(days-1);i++){
         			  cal.add(Calendar.DATE,   -1);
         			  String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime())+"-"+type+".log";
         			  yesterday=yesterday.replace(" ", "");
         			  lists.add(yesterday);
         		  }
         		 if(!lists.contains(file2.getName())){
         			 file2.delete();
         		 }
      		 }
      		  
			}
		}
	}
}
