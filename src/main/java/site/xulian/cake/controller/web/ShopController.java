package site.xulian.cake.controller.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;

import site.xulian.cake.model.Shop;
import site.xulian.cake.service.ShopService;
import site.xulian.cake.utils.Constant;
import site.xulian.cake.utils.JsonType;
@ControllerBind(controllerKey="/shop")
public class ShopController extends Controller{
		/**
	   * 获取所有店铺
	   * */
	  public void getAllShop(){
		  List<Shop> shopList = Shop.dao.find("SELECT * FROM shop");
		  if(null != shopList && shopList.size() > 0){
			  renderText(JsonType.toJson(Constant.CODE_SUCCESS, JsonKit.toJson(shopList)));
			  return;
		  }
		  renderText(JsonType.toJson(Constant.CODE_ERROR, "")); 
	  }
}
