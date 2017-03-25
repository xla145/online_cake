package site.xulian.cake.controller.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import site.xulian.cake.interceptor.LoginInter;
import site.xulian.cake.model.Shop;
import site.xulian.cake.service.ShopService;

/**
 * @author Administrator
 *	对商品的管理
 */
@Before(LoginInter.class)
@ControllerBind(controllerKey="/mshop",viewPath="/WEB-INF/page/shop")
public class ShopController extends Controller {
  Logger logger = LoggerFactory.getLogger(this.getClass());
  public void index(){
	  System.out.println("-------");
	 render("index.html");
  }
  /**
   * 获取所有店铺
   * */
  public void getAllShop(){
	  Page<Record> shopPage = ShopService.service.getAllShop(getParaToInt("currentPage"),getParaToInt("pageSize",5),getPara("shopName"),getPara("shopPhone"),getParaToInt("status"));
	  List<Record> shopList= shopPage.getList();
	  logger.info("店铺信息"+JsonKit.toJson(shopList));
	  setAttr("totalRow", shopPage.getTotalRow());
	  setAttr("totalPage",shopPage.getTotalPage());
	  setAttr("currentPage",shopPage.getPageNumber());
	  setAttr("shopList", shopList);
	  renderJson();
  }
  /**
   * 保存店铺信息
   * */
  public void save(){
	  String result = "";
	  if(getPara("id") == null){
		  result = Shop.dao.saveShop(getPara("name"),getPara("phone"),getParaToInt("status",1),getPara("address"));
	  }else{
		  result = Shop.dao.updateShop(getParaToInt("id"),getPara("name"),getPara("phone"),getParaToInt("status"),getPara("address"));
	  }  
	  setAttr("msg", result);
	  renderJson();
  }
  /**
   * 获取店铺信息
   * */
  public void get(){
	  Shop shop = Shop.dao.getShop(getParaToInt("shopId"));
	  setAttr("shop", shop);
	  renderJson();
  }
  /**
   * 批量删除店铺信息
   * */
  public void del(){
	  String result = Shop.dao.delShop(getParaToInt("shopId"));
	  setAttr("msg", result);
	  renderJson();
  }
}
