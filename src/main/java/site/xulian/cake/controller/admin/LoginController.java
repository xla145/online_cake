package site.xulian.cake.controller.admin;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.log.Logger;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import site.xulian.cake.model.Member;

/**
 * @author Administrator
 *	对商品的管理
 */
@ControllerBind(controllerKey="/")
public class LoginController extends Controller {
  
  public void index(){
	 render("login.html");
  } 
  /**
   * 用户登录
   * */
  public void login(){	
	  boolean isSuccess = Member.dao.login(getPara("name"), getPara("password"), getParaToInt("type"));
	  if(isSuccess){
		  setSessionAttr("user", getPara("name"));
		  setAttr("msg", "");
	  }else{
		  setAttr("msg", "账号或密码错误");
	  }		  
	  renderJson();
  }
}
