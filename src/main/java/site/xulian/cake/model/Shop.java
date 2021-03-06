package site.xulian.cake.model;

import java.util.Calendar;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import site.xulian.cake.model.base.BaseShop;
import site.xulian.cake.utils.DataObj;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
@TableBind(tableName = "shop")
public class Shop extends BaseShop<Shop> {
	Logger logger = Logger.getLogger(this.getClass());
	public static final Shop dao = new Shop();
	/**
	 * 保存店铺信息
	 * */
	public DataObj<Shop> saveShop(Integer id,String name,String phone,Integer status,String address){
		Shop shop = new Shop();
		shop.setName(name);
		shop.setPhone(phone);
		shop.setStatus(status);
		shop.setAddress(address);
		try{
			if(null != id){
				shop.setId(id);
				shop.setUpdateTime(Calendar.getInstance().getTime());
				shop.update();
			}else{
				shop.setUpdateTime(Calendar.getInstance().getTime());
				shop.setCreateTime(Calendar.getInstance().getTime());
				shop.save();
			}
		}catch (Exception e) {
			logger.error("保存信息失败！"+e.getMessage());
			if(e.getMessage().indexOf("Duplicate entry") != -1){
				return new DataObj<>("该数据已存在，不能重复添加");
			}else{
				return new DataObj<>("添加数据失败，稍后请重试");
			}
		}
		return DataObj.getSuccessData(shop);
//		//Integer num = Db.update("INSERT INTO shop(name,phone,address,status,update_time,create_time) VALUES(?,?,?,?,now(),now())",name,phone,address,status);		
//		if(num > 0){
//			return DataObj.getSuccessData("shop");
//		}
//		return new DataObj<>("保存失败！");
	}
	/**
	 * 获取店铺信息
	 * */
	public Shop getShop(Integer id){
		return findFirst("SELECT * FROM shop WHERE id = ? limit 1",id);
	}
	/**
	 * 修改店铺信息
	 * */
	public DataObj<String> updateShop(Integer id,String name,String phone,Integer status,String address){
		Integer num = Db.update("UPDATE shop SET name =? ,phone = ? ,status =? ,address = ? ,update_time = now() WHERE id = ?",name,phone,status,address,id);
		if(num > 0){
			return new DataObj<>("修改成功！");
		}
		return new DataObj<>("修改失败！");
	}
	/**
	 * 批量删除店铺信息
	 * */
	public String delShop(Integer id){
		return Db.update("UPDATE shop SET status =? WHERE id = ?",0,id)>0?"":"删除店铺信息失败！";
	}
}
