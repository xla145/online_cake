package site.xulian.cake.controller.admin;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

/**
 * @author Administrator
 *	对商品的管理
 */
@ControllerBind(controllerKey="/goodsType",viewPath="/WEB-INF/page/")
public class GoodsTypeController extends Controller {
  public void index(){
	 render("index.html");
  } 
}
