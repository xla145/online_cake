package site.xulian.cake.interceptor;



import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import site.xulian.cake.utils.RequestUtil;

public class LoginInter implements Interceptor{
	
	@Override
    public void intercept(Invocation ai) {	
        Controller c = ai.getController();
        String user = (String) c.getSessionAttr("user");
        System.out.println("用户"+user);
        if(null == user){
        	c.redirect("/login.html");
        	return;
        }
        c.setAttr("contextPath", RequestUtil.getContextAllPath(c.getRequest()));
        ai.invoke();
    }
	
}
