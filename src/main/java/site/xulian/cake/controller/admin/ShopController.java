package site.xulian.cake.controller.admin;

import java.util.List;



import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import org.apache.log4j.Logger;
import site.xulian.cake.base.BaseController;
import site.xulian.cake.interceptor.AllowCrossDomain;
import site.xulian.cake.model.Shop;
import site.xulian.cake.service.ShopService;
import site.xulian.cake.utils.DataObj;

/**
 * @author Administrator
 *	对商品的管理
 */
//@Before(LoginInter.class)
@Before(AllowCrossDomain.class)
@ControllerBind(controllerKey="/mshop",viewPath="/WEB-INF/page/shop")
public class ShopController extends BaseController {
  Logger logger = Logger.getLogger(this.getClass());
  public void index(){
	  System.out.println("-------");
	 render("index.html");
  }
  /**
   * 获取所有店铺
   * */
  public void getData(){
	  Page<Record> shopPage = ShopService.service.getAllShop(getParaToInt("pageNum"),getParaToInt("pageSize",5),getPara("shopName"),getPara("shopPhone"),getParaToInt("status"));
	  List<Record> shopList= shopPage.getList();
	  logger.info("店铺信息"+JsonKit.toJson(shopList));
	  setAttr("totalRow", shopPage.getTotalRow());
	  setAttr("totalPage",shopPage.getTotalPage());
	  setAttr("currentPage",shopPage.getPageNumber());
	  setAttr("list", shopList);
	  renderJson();
  }
  /**
   * 保存店铺信息
   * */
  public void save(){
	  DataObj<Shop> data = null;
	  data = Shop.dao.saveShop(getParaToInt("id"),getPara("name"),getPara("phone"),getParaToInt("status"),getPara("address"));
	  if(data.isSuccessCode()){
		 renderNull();
	  }else{
		 renderErrorText(data.getMsg());
	  }
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
