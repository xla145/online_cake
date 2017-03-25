package site.xulian.cake.utils;
public class JsonType {

	public static String toJson(int code, String data) {
		return "{\"code\":\"" + code + "\",\"data\":" + data + "}";
	}

}