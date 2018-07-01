package utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 操作json数据的工具类
 * @author Administrator
 *
 */
public class GsonUtils {
	 /** 
     * 将json转换成bean对象 
     *  
     * @param <T> 
     * @param json 
     * @param clazz 
     * @return 
     */  
	public static <T> T jsonToBean(String json, Class<T> clazz) {
		Gson gson = new Gson();
        T obj = null;  
        if (gson != null) {  
            obj = gson.fromJson(json, clazz);  
        }  
        return obj;  
    } 
	/** 
	 * 将json转换成bean对象 
	 *  
	 * @param <T> 
	 * @param json 
	 * @param clazz 
	 * @return 
	 */  
	public static <T> T jsonToEntity(String json, Type type) {
		Gson gson = new Gson();
		T obj = null;  
		if (gson != null) {  
			obj = gson.fromJson(json, type);  
		}  
		return obj;  
	} 
	
    /** 
     * 将json格式转换成List对象 
     *  
     * @param json 
     * @return 
     */  
    public static List<?> jsonToList(String json, Type type) {  
    	Gson gson = new Gson();
        List<?> list = null;  
        if (gson != null) {  
            list = gson.fromJson(json, type);  
        }  
        return list;  
    }  
    
    /**
     * 将bean对象转换为json数据
     * @param <T>
     * @param json
     * @return
     */
    public static <T> String objToJson(T obj){
    	Gson gson = new Gson();
    	String jsonResult = null;
    	if(gson != null){
    		jsonResult = gson.toJson(obj);  
    	}
    	return jsonResult;
    }
    
    /**
     * 将map对象转换为json数据
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, Object> map){
    	Gson gson = new Gson();
    	String jsonResult = null;
    	if(gson != null){
    		jsonResult = gson.toJson(map);  
    	}
    	return jsonResult;
    }
    
    /**
     * 将List的对象数据转换为json
     * @param list
     * @return
     */
    public static String listToJson(List<?> list){
    	Gson gson = new Gson();
//    	Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    	String jsonResult = null;
    	if(gson != null){
    		jsonResult = gson.toJson(list);  
    	}
    	return jsonResult;
    }
}
