package site.xulian.cake.interceptor;



import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import site.xulian.cake.utils.RequestUtil;

public class AttrInter implements Interceptor{
	
	@Override
    public void intercept(Invocation ai) {
//		if(PropKit.getBoolean("devMode")){// TODO 提测前需要修改配置文件中的开发模式为false
//			Common.removeCache(Constant.CACHE_KEY_5, Constant.CACHE_KEY_10,Constant.CACHE_KEY_30, Constant.CACHE_KEY_60,
//					Constant.CACHE_KEY_300, Constant.CACHE_KEY_600,Constant.CACHE_KEY_1800, Constant.CACHE_KEY_3600, Constant.CACHE_KEY_86400);
//		}	
        Controller c = ai.getController();
        c.setAttr("contextPath", RequestUtil.getContextAllPath(c.getRequest()));
        ai.invoke();
    }
	
}
