package site.xulian.cake.controller.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.JsonKit;
import com.jfinal.log.Logger;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import site.xulian.cake.model.Goods;
import site.xulian.cake.model.Order;
import site.xulian.cake.utils.Constant;
import site.xulian.cake.utils.JsonType;

/**
 * @author Administrator
 *	对商品的管理
 */
@ControllerBind(controllerKey="/order")
public class OrderController extends Controller {
	Logger logger =Logger.getLogger(this.getClass());
  public void index(){
	 render("index.html");
  }
  /**
   * 下单
   * */
  public void addOrder(){
	  Order order = Order.dao.addOrder(getPara("shopId"),getPara("phone"),getPara("goodsId"),getPara("price"), getPara("address"));
	  if(null != order){
		  logger.info("订单信息"+JsonKit.toJson(order));
		  renderText(JsonType.toJson(Constant.CODE_SUCCESS, JsonKit.toJson(order)));
		  return;
	  }
	  renderText(JsonType.toJson(Constant.CODE_ERROR, ""));
  }
  /**
   * 获取订单
   * */
  public void getOrder(){
	  List<Order> order = Order.dao.getOrder(getPara("phone"));
	  if(null != order){
		  logger.info("订单信息"+JsonKit.toJson(order));
		  renderText(JsonType.toJson(Constant.CODE_SUCCESS, JsonKit.toJson(order)));
		  return;
	  }
	  renderText(JsonType.toJson(Constant.CODE_ERROR, ""));
  }
  /**
   * 更新状态
   * */
  public void pay(){
	  String result = Order.dao.pay(getPara("loginid"),getPara("orderid"),getPara("paymoney"));
	  if("" != result){
		  logger.info("订单信息"+result);
		  renderText(JsonType.toJson(Constant.CODE_ERROR, result));
		  return;
	  }
	  renderText(JsonType.toJson(Constant.CODE_SUCCESS, "yes"));
  }
}
