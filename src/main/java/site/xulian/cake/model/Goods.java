package site.xulian.cake.model;

import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;

import site.xulian.cake.model.base.BaseGoods;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
@TableBind(tableName = "goods")
public class Goods extends BaseGoods<Goods> {
	public static final Goods dao = new Goods();
	/**
	 * 根据店铺id获取对应的商品
	 * */
	public List<Goods> getGoodsByShopId(Integer shopId){
		return Goods.dao.find("select *from goods where shop_id = ?",shopId);
	}
	/**
	 * 保存店铺信息
	 * */
	public String saveGoods(String name,String description,Integer type,Integer status,String price,Integer shopId){
		return Db.update("INSERT INTO goods(name,description,type,status,price,shop_id,update_time,create_time) VALUES(?,?,?,?,?,?,now(),now())",name,description,type,status,Double.parseDouble(price),shopId)>0?"":"保存失败!";
	}
	/**
	 * 获取店铺信息
	 * */
	public Goods getGoods(Integer id){
		return findFirst("SELECT * FROM goods WHERE id = ? limit 1",id);
	}
	/**
	 * 修改店铺信息
	 * */
	public String updateGoods(Integer id,String name,String description,Integer type,Integer status,String price,Integer shopId){
		return Db.update("UPDATE goods SET name =? ,description = ? ,type =? ,status = ? ,price = ?, shop_id = ? ,update_time = now() WHERE id = ?",name,description,type,status,Double.parseDouble(price),shopId,id)>0?"":"修改信息失败！";
	}
	/**
	 * 批量删除店铺信息
	 * */
	public String delGoods(Integer id){
		return Db.update("UPDATE Goods SET status =? WHERE id = ?",0,id)>0?"":"删除信息失败！";
	}
}
