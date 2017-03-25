package site.xulian.cake.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author liuzhao
 *
 */
public class JSONData{
	
	public static JSONObject getJSON(String msg){
		return getJSON(0, msg);
	}
	
	public static JSONObject getJSON(int code){
		return getJSON(code, null);
	}
	
	public static JSONObject getJSON(int code, String msg){
		return getJSON(code, msg, null);
	}
	
	public static JSONObject getJSON(int code, String msg, Object data){
		JSONObject returnData = new JSONObject();
		returnData.put("code", code);
		if(msg != null){
			returnData.put("msg", msg);
		}
		if(data != null){
			returnData.put("data", data);
		}
		return returnData;
	}
}
