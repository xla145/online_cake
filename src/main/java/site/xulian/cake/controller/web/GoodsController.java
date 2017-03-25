package site.xulian.cake.controller.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.JsonKit;
import com.jfinal.log.Logger;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import site.xulian.cake.model.Goods;
import site.xulian.cake.utils.Constant;
import site.xulian.cake.utils.JsonType;

/**
 * @author Administrator
 *	对商品的管理
 */
@ControllerBind(controllerKey="/goods")
public class GoodsController extends Controller {
	Logger logger =Logger.getLogger(this.getClass());
  public void index(){
	 render("index.html");
  }
  /**
   * 获取商品
   * */
  public void getGoodsByShopId(){
	  int shopId = getParaToInt("shopId");
	  logger.info("店铺编号"+shopId);
	  List<Goods> goodsList = Goods.dao.getGoodsByShopId(shopId);
	  if(null != goodsList && !goodsList.isEmpty()){
		  logger.info("商品信息"+JsonKit.toJson(goodsList));
		  renderText(JsonType.toJson(Constant.CODE_SUCCESS, JsonKit.toJson(goodsList)));
		  return;
	  }
	  renderText(JsonType.toJson(Constant.CODE_ERROR, ""));
  }
}
