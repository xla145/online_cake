package site.xulian.cake.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log4jLog;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;

/**
 * 公共方法
 * */
public class Common {
private static Log4jLog log = Log4jLog.getLog(Common.class);
	
	/**
	 * 将list转换为sql的in查询字符串
	 * @param list
	 * @return '1','2',''		没有数据时返回''
	 */
	public static String listToSqlIn(List<String> list){
		StringBuffer sqlIn = new StringBuffer("'");
		Iterator<String> ite = list.iterator();
		while(ite.hasNext()){
			sqlIn.append(ite.next()+"','");
		}
		sqlIn.append("'");
		return sqlIn.toString();
	}
	
	/**
	 * 将list转换为sql的in查询字符串
	 * 
	 * @param resData
	 * @param columnName
	 * @return '1','2',''		没有数据时返回''
	 */
	public static String recordListToSqlIn(List<Record> resData,String columnName){
		StringBuffer sqlIn = new StringBuffer("'");
		Iterator<Record> ite = resData.iterator();
		while(ite.hasNext()){
			Record rowData = ite.next();
			sqlIn.append(rowData.get(columnName)+"','");
		}
		sqlIn.append("'");
		return sqlIn.toString();
	}
	
	/**
	 * 将数组转换成sql的in查询字符串
	 * @return '1','2',''		没有数据时返回''
	 */
	public static String arrayToSqlIn(String[] array){
		StringBuffer sqlIn = new StringBuffer("'");
		for(int i=0;i<array.length;i++){
			sqlIn.append(array[i]+"','");
		}
		sqlIn.append("'");
		return sqlIn.toString();
	}
	
	/**
	 * 将list的数组转换为二维数组，主要用于Db.batch()，批量处理
	 * 
	 * @param list
	 * @return
	 */
	public static Object[][] listTo2Array(List<Object[]> list){
		if(list.size() == 0){
			return null;
		}
		int size = list.get(0).length;
		Object[][] paramsArr = new Object[list.size()][size];
		for(int i=0;i<list.size();i++){
			paramsArr[i] = list.get(i);
		}
		return paramsArr;
	}
	
	/**
	 * sql like时防止注入
	 * @param srcStr
	 * @return
	 */
	public static String queryLike(String srcStr) {
		//适用于sqlserver
//		result = StringUtils.replace(result, "[", "[[]");
//		result = StringUtils.replace(result, "_", "[_]");
//		result = StringUtils.replace(result, "%", "[%]");
//		result = StringUtils.replace(result, "^", "[^]");
		//适用于mysql
		srcStr = StringUtils.replace(srcStr, "\\", "\\\\");
		srcStr = StringUtils.replace(srcStr, "'", "\\'");
		srcStr = StringUtils.replace(srcStr, "_", "\\_");
		srcStr = StringUtils.replace(srcStr, "%", "\\%");
		
		return "%" + srcStr + "%";
	}
	
	public static String nullToBlank(Object obj){
		return null == obj?"":obj.toString();
	}
	public static Object blankToNull(Object obj){
		if(null != obj && StringUtils.isBlank(obj.toString())){
			return null;
		}
		return obj;
	}
	
	/**
	 * 校验手机号码合法性
	 */
	public static boolean mobileVerify(String phone){
		if(StringUtils.isNotBlank(phone)){
			String regExpMobile = PropKit.get("regex.phone", "^1[3-9]\\d{9}$");
			Pattern p = Pattern.compile(regExpMobile);
			Matcher m = p.matcher(phone);
			return m.find();
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param qq
	 * @return
	 */
	public static boolean qqVerify(String qq){
		if(StringUtils.isNotBlank(qq)){
			String regExpQQ = PropKit.get("regex.qq", "^[1-9][0-9]{4,11}");
			Pattern p = Pattern.compile(regExpQQ);
			Matcher m = p.matcher(qq);
			return m.find();
		}else{
			return false;
		}
	}
	
	/**
	 * 生成ehcache的key值，
	 * 注意：此处是以调用方的类包名+方法名生成缓存key的前缀，所以如果一个方法里调用了两次，此处要自己传递一个args作为区分
	 * 
	 * @param args
	 * @return
	 */
	public static String generEhcacheKey(String... args){
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String keyPrefix = UUID.randomUUID().toString().replaceAll("-", "");
		if(elements.length > 2){// 获取调用方的类名及方法名，作为key的前缀，防止key重复
			StackTraceElement element = elements[2];
			keyPrefix = element.getClassName()+" > "+ element.getMethodName();
		}else{// 通常情况下，length大于2的
			log.warn("生成cacheKey时，调用方参数有误："+JsonKit.toJson(elements));
		}
		
		String split = "|||";
		StringBuffer key = new StringBuffer(keyPrefix);
		for(String arg : args) {
			if(arg == null){
				arg = "NULL";
			}
			key.append(split + arg);
        }
		log.info("生成的cache key="+key.toString());
		return key.toString();
	}
	
	/**
	 * 去除double类型字符串后面的.和多余的0
	 * @param s
	 * @return
	 */
	public static String getDouble(String s){
		if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉  
        }  
        return s;
	}
	
	/**
	 * 向url后追加参数
	 * @param url
	 * @param key
	 * @param value
	 * @return
	 */
	public static void appendUrlParam(StringBuffer urlBuffer, String key, String value){
		if(urlBuffer == null || urlBuffer.length() == 0){
			return;
		}
		if (urlBuffer.indexOf("?") == -1) {
			urlBuffer.append("?");
		} else if(!"&".endsWith(urlBuffer.substring(urlBuffer.length()-1))){
			urlBuffer.append("&");
		}
		urlBuffer.append(key + "=" + value);
	}
	
	public static void appendUrlParams(StringBuffer urlBuffer, Map<String, String[]> params, String... excludes){
		appendUrlParams(urlBuffer, params, 600, excludes);
	}
	/**
	 * 
	 * @param urlBuffer
	 * @param params
	 * @param maxParamsLength 参数最大长度，如果超出这个长度，就不再追加参数，防止因调用方程序有问题，一直请求，导致追加参数过长
	 * @param excludes
	 */
	public static void appendUrlParams(StringBuffer urlBuffer, Map<String, String[]> params, int maxParamsLength, String... excludes){
		if(urlBuffer == null || urlBuffer.length() == 0){
			return;
		}
		if(params == null || params.size() == 0){
			return;
		}
		List<String> excludeList = new ArrayList<String>();
		if(excludes != null){
			excludeList.addAll(Arrays.asList(excludes));
		}
		int startLength = urlBuffer.length();
		for(String key : params.keySet()){
			if(excludeList.contains(key)){
				continue;
			}
			if(params.get(key) == null || params.get(key).length==0){
				if((urlBuffer.length() - startLength + key.length() + 1) > maxParamsLength){// 判断追加参数后，追加的参数长度是否大于设置的最大长度，如果大于，则停止追加
					return;
				}
				appendUrlParam(urlBuffer, key, "");
			}else{
				for(String v : params.get(key)){
					if((urlBuffer.length() - startLength + key.length() + v.length() + 1) > maxParamsLength){// 判断追加参数后，追加的参数长度是否大于设置的最大长度，如果大于，则停止追加
						return;
					}
					appendUrlParam(urlBuffer, key, v);
				}
			}
		}
	}
	
	/**
	 * 处理特殊字符，如表情字符，无法保存到数据库，需要先替换特殊字符
	 * 
	 * 将特殊字符替换为*
	 * 
	 * 如：aa的😁😁😁😁123   替换后为：aa的****123
	 * 
	 * @param text
	 * @return
	 */
	public static String replaceDbStr(String text){
		if(StringUtils.isBlank(text)){
			return text;
		}
		byte[] conbyte = text.getBytes();
		byte[] newByte = new byte[conbyte.length];
		int length = 0;
        for (int i = 0; i < conbyte.length; i++) {
        	newByte[length] = conbyte[i];
        	if((conbyte[i] & 0xF8) == 0xF0){
        		newByte[length] = 0x2a;
        		i += 3;
        	}
        	length ++;
        }
        
        text = new String(newByte).trim();
        return text;
	}
	
	/**
	 * 清除指定的cacheKey，用于开发环境清除缓存
	 * @param cacheKeys
	 */
	public static void removeCache(String... cacheKeys){
		for(String key : cacheKeys){
			CacheKit.removeAll(key);
		}
	}
	
	/**
	 * 对于com.alibaba.fastjson.JSONObject如果直接获取不存在的key值会报错
	 * @param data
	 * @param key
	 * @return
	 */
	public static String getJSONValue(JSONObject data, String key){
		if(data == null){
			return null;
		}
		if(data.containsKey(key)){
			return data.getString(key);
		}else{
			return null;
		}
	}	
}
