package site.xulian.cake.controller.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Logger;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import site.xulian.cake.interceptor.LoginInter;
import site.xulian.cake.model.Member;

/**
 * @author Administrator
 *	对商品的管理
 */
@Before(LoginInter.class)
@ControllerBind(controllerKey="/index",viewPath="/WEB-INF/page/main")
public class IndexController extends Controller {
  
  public void index(){
	 render("main.html");
  } 
  public void top(){
	 setAttr("user", getSessionAttr("user"));
	 render("top.html");
  }
  public void left(){
	 render("left.html");
  }
  public void tool(){
	 render("tool.html");
  }
}
