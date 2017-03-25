package site.xulian.cake.controller.web;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.JsonKit;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import site.xulian.cake.model.Users;
import site.xulian.cake.utils.Common;
import site.xulian.cake.utils.Constant;
import site.xulian.cake.utils.JsonType;

/**
 * @author Administrator
 *	对商品的管理
 */
@ControllerBind(controllerKey="/user",viewPath="/WEB-INF/page/")
public class UserController extends Controller {
  public void index(){
	 render("index.html");
  } 
  public void login(){
	  String phone = getPara("phoneName");
	  String password = getPara("password");
	  Users user= Users.dao.login(phone, password);
	  if(null != user){
		  renderText(JsonType.toJson(Constant.CODE_SUCCESS, JsonKit.toJson(user)));
		  return;
	  }
	  renderText(JsonType.toJson(Constant.CODE_ERROR, ""));	  
  }
  /**
   * 用户注册
   * */
  public void register(){  
	  boolean isRegister = Users.dao.register(getPara("phoneName"), getPara("password"), getPara("email"), getPara("nikename"));	  
	  Users user = new Users();
	  user.setPhone(getPara("phoneName"));
	  user.setEmail(Common.nullToBlank(getPara("email")));
	  user.setNikename(Common.nullToBlank(getPara("nikename")));
	  if(isRegister){
		  renderText(JsonType.toJson(Constant.CODE_SUCCESS, JsonKit.toJson(user)));
		  return;
	  }
	  renderText(JsonType.toJson(Constant.CODE_ERROR, ""));	  
  }
}
