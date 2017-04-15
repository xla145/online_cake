package site.xulian.cake.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestUtil {
	
	/**
	 * 获取请求的IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

	/**
	 * 获取当前应用部署的地址
	 * 如：http://localhost:8080
	 * 如果是80端口则不加端口号
	 * @param request
	 * @return
	 */
	public static String getContextAllPath(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getScheme()).append("://").append(request.getServerName());
		if(request.getServerPort() != 80){
			sb.append(":").append(request.getServerPort());
		}
		sb.append(request.getContextPath());
		String path = sb.toString();
		sb = null;
		return path;
	}
	
	/**
	 * 判断请求是否为ajax
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request){
	    return  (request.getHeader("X-Requested-With") != null  && "XMLHttpRequest".equals( request.getHeader("X-Requested-With").toString())) ;
	}
	
	/**
	 * 设置session及cookie值，放两处主要是防止
	 * @param request
	 * @param response
	 * @param key
	 * @param value
	 */
	public static void setData(HttpServletRequest request, HttpServletResponse response, String key, String value){
		request.getSession().setAttribute(key, value);
		Cookie cookie = new Cookie(key, value);
		response.addCookie(cookie);
	}
	
	/**
	 * 获取设置的数据
	 * 优先拿session中的，如果session中不存在，则拿cookie中的
	 * @param request
	 * @param response
	 * @param key
	 * @param value
	 */
	public static String getData(HttpServletRequest request, String key){
		Object value = request.getSession().getAttribute(key);
		if(value != null){
			return value.toString();
		}
		Cookie[] cookies = request.getCookies();
		if (cookies == null){
			return null;
		}
		for (Cookie cookie : cookies){
			if (cookie.getName().equals(key)){
				return cookie.getValue();
			}
		}
		return null;
	}
	
	/**
	 * 当前请求客户端是否为微信
	 * @return
	 */
	public static boolean isWeixinClient(HttpServletRequest request){
		String userAgent = request.getHeader("user-agent");
		if(userAgent.toLowerCase().indexOf("micromessenger") != -1){// 微信浏览器
			return true;
		}
		return false;
	}

}
