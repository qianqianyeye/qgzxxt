package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;



public class HttpRequestUtils {
	 /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
//            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    /**
     *   无论是使用HttpGet，还是使用HttpPost，都必须通过如下3步来访问HTTP资源。
        1.创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。
        2.使用DefaultHttpClient类的execute方法发送HTTP GET或HTTP POST请求，并返回HttpResponse对象。
       3.通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理。
                          如果使用HttpPost方法提交HTTP POST请求，则需要使用HttpPost类的setEntity方法设置请求参数。
     	     参数则必须用NameValuePair[]数组存储。
     * @param params
     */
    public void getParames(HttpParams params){
    	HttpParams httpParameters = new BasicHttpParams();  
    	HttpConnectionParams.setConnectionTimeout(httpParameters, 10*1000);//设置请求超时10秒  
    	HttpConnectionParams.setSoTimeout(httpParameters, 10*1000); //设置等待数据超时10秒  
    	HttpConnectionParams.setSocketBufferSize(params, 8192);  
    	HttpClient httpclient = new DefaultHttpClient(httpParameters); //此时构造DefaultHttpClient时将参数传入 
    }
    
    
    //这里是http GET请求
    public static String httpdoGet(String uriAPI)  
    {  
//        String uriAPI = "http://XXXXX?str=I+am+get+String";  
        String result= "";  
//      HttpGet httpRequst = new HttpGet(URI uri);  
//      HttpGet httpRequst = new HttpGet(String uri);  
//      创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。  
        HttpGet httpRequst = new HttpGet(uriAPI);  
  
//      new DefaultHttpClient().execute(HttpUriRequst requst);  
        try {  
        	//使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。  
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);//其中HttpGet是HttpUriRequst的子类  
            if(httpResponse.getStatusLine().getStatusCode() == 200)  
            {  
                HttpEntity httpEntity = httpResponse.getEntity();  
                result = EntityUtils.toString(httpEntity);//取出应答字符串  
                // 一般来说都要删除多余的字符   
                result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格    
            }else{
            	httpRequst.abort();  
            }
           } catch (ClientProtocolException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            result = e.getMessage().toString();  
        }  
        return result;  
    }  
    
    
    /**
     * 这里进行http Post请求
     * 如果使用HttpPost方法提交HTTP POST请求，则需要使用HttpPost类的setEntity方法设置请求参数。
     * 参数则必须用NameValuePair[]数组存储。
     * @param <T>
     * @param uriAPI
     * @return
     */
    public static <T> String httpdoPost(String uriAPI,T entityObj)  
    {  
//        String uriAPI = "http://XXXXXX";//Post方式没有参数在这里  
        String result = "";  
        HttpPost httpRequst = new HttpPost(uriAPI);//创建HttpPost对象  
        List <NameValuePair> params = new ArrayList<NameValuePair>();  
        params.add(new BasicNameValuePair("str", "I am Post String"));  
        String paramPost = GsonUtils.objToJson(entityObj);
        try {  
//          httpRequst.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));  
            StringEntity entity = new StringEntity(paramPost,HTTP.UTF_8);
//        	entity.setContentType("application/x-www-form-urlencoded");
            httpRequst.setHeader("Content-Type", "application/json");
        	httpRequst.setEntity(entity);
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);  
            if(httpResponse.getStatusLine().getStatusCode() == 200)  
            {  
                HttpEntity httpEntity = httpResponse.getEntity();  
                result = EntityUtils.toString(httpEntity);//取出应答字符串  
            }  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
//            result = e.getMessage().toString();  
        	result = "error";
        }  
        catch (ClientProtocolException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
//            result = e.getMessage().toString();  
        	result = "error";
        }  
        catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
//            result = e.getMessage().toString();  
        	result = "error";
        }  
        return result;  
    }  
}
