package site.xulian.cake.utils;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * 请求接口数据
	 * */
	public class RequestAPI {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	//配置您申请的KEY
	public static final String APPKEY = PropKit.get("news.AppKey");
    public static Logger logger = Logger.getLogger(RequestAPI.class);
	//1.按城市检索
	public static String getNewsListByAPI(String type) {
		String result = null;
		String url = PropKit.get("news.url");//请求接口地址
		Map params = new HashMap();//请求参数
        params.put("type", type);
		//params.put("city", "");//城市名称，如：北京 URLencode
		//params.put("page", "");//当前页数，默认1
		//params.put("pagesize", "");//每页返回，最大50
		params.put("key", APPKEY);//应用APPKEY(应用详细页查询)
		//params.put("dtype", "");//返回数据的格式,xml或json，默认json
		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.parseObject(result);
			if (object.getInteger("error_code") == 0) {
			    System.out.println("result"+object.get("result").toString());
				return object.get("result").toString();
			} else {
				return null;
			}
		} catch (Exception e) {
            logger.error("获取新闻信息失败，原因是"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	//2.检索周边美食
	public static void getRequest2() {
		String result = null;
		String url = "http://apis.juhe.cn/catering/query";//请求接口地址
		Map params = new HashMap();//请求参数
		params.put("lng", "");//经纬(如:121.538123)，传递的适合google地图的坐标系
		params.put("lat", "");//纬度(如：31.677132)
		params.put("radius", "");//搜索范围，单位M，默认3000
		params.put("page", "");//当前页数，默认1,最大50.
		params.put("key", APPKEY);//应用APPKEY(应用详细页查询)
		params.put("dtype", "");//返回数据的格式,xml或json，默认json

		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.parseObject(result);
			if (object.getInteger("error_code") == 0) {
				System.out.println(object.get("result"));
			} else {
				System.out.println(object.get("error_code") + ":" + object.get("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {

	}

	/**
	 * @param strUrl 请求地址
	 * @param params 请求参数
	 * @param method 请求方法
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	public static String net(String strUrl, Map params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	//将map型转为请求参数型
	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}