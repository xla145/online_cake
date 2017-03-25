package site.xulian.cake.controller.admin;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * @author Administrator
 *	对商品的管理
 */
@ControllerBind(controllerKey="/mcomment",viewPath="/WEB-INF/page/")
public class CommentController extends Controller {
  public void index(){
	 render("index.html");
  } 
}
