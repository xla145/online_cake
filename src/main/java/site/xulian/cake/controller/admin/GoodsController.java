package site.xulian.cake.controller.admin;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import org.apache.log4j.Logger;
import site.xulian.cake.model.Goods;
import site.xulian.cake.service.GoodsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 *	对商品的管理
 */
@ControllerBind(controllerKey="/mgoods",viewPath="/WEB-INF/page/goods")
public class GoodsController extends Controller {
  Logger logger = Logger.getLogger(this.getClass());
  public void index(){
	 render("index.html");
  } 
  /**
   * 获取所有店铺
   * */
  public void getAllGoods(){
	  Page<Record> goodsPage = GoodsService.service.getAllGoods(getParaToInt("currentPage"),getParaToInt("pageSize",5),getPara("goodsName"),getParaToInt("goodsType"));
	  List<Record> goodsList= goodsPage.getList();
	  logger.info("商品信息"+JsonKit.toJson(goodsList));
	  setAttr("totalRow", goodsPage.getTotalRow());
	  setAttr("totalPage",goodsPage.getTotalPage());
	  Map<Integer,String> map = new HashMap<Integer,String>();
	  map.put(1, "上线");
	  map.put(2, "下线");
	  setAttr("currentPage",goodsPage.getPageNumber());
	  setAttr("status", map);
	  setAttr("goodsList", goodsList);
	  renderJson();
  }
  /**
   * 保存店铺信息
   * */
  public void save(){
	  System.out.println("--------------------------------"+getPara("id"));
	  String result = "";
	  if("".equals(getPara("id"))){
		  logger.info("商品信息"+"name="+getPara("name")+"description"+getPara("description")+"type="+getParaToInt("type")+"price"+getPara("price"));
		  result = Goods.dao.saveGoods(getPara("name"),getPara("description"),getParaToInt("type"),getParaToInt("status",1),getPara("price"),getParaToInt("shopId"));
	  }else{
		  result = Goods.dao.updateGoods(getParaToInt("id"),getPara("name"),getPara("description"),getParaToInt("type"),getParaToInt("status",1),getPara("price"),getParaToInt("shopId"));
	  }  
	  setAttr("msg", result);
	  renderJson();
  }
  /**
   * 获取店铺信息
   * */
  public void getgoods(){
	  Goods goods = Goods.dao.getGoods(getParaToInt("goodsId"));
	  setAttr("goods", goods);
	  renderJson();
  }
  /**
   * 批量删除店铺信息
   * */
  public void delgoods(){
	  String result = Goods.dao.delGoods(getParaToInt("goodsId"));
	  setAttr("msg", result);
	  renderJson();
  }
}
