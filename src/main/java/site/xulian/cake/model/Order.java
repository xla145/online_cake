package site.xulian.cake.model;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Db;

import site.xulian.cake.model.base.BaseOrder;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
@TableBind(tableName = "order")
public class Order extends BaseOrder<Order> {
	public static final Order dao = new Order();
	public Order addOrder(String shopId,String phone,String goodsId,String price,String address){
		System.out.println("订单信息,shopId"+shopId+"phone"+phone+"goodsId"+goodsId+"price"+price+"address"+address);
		Order order = new Order();
		List<Users> userList =  Users.dao.find("SELECT * FROM users where phone = ?",phone);
		if(null != userList && !userList.isEmpty()){
			order.setUserId(userList.get(0).getId());
		}
		Goods goods = Goods.dao.findById(goodsId);
		System.out.println(goods == null);
		if(null != goods){
			System.out.println("--------------");
			order.setGoodsName(goods.getName());
		}
		order.setMoney(Double.parseDouble(price));
		order.setCreateTime(Calendar.getInstance().getTime());
		order.setGoodsId(Integer.parseInt(goodsId));
		order.setShopId(Integer.parseInt(shopId));
		order.setStatus(1);
		order.setUpdateTime(Calendar.getInstance().getTime());
		order.setOrderId("OF"+UUID.randomUUID());
		order.setAddress(address);
		order.setPhone(phone);
		return order.save()?order:null;	
	}
	public List<Order> getOrder(String phone){
		List<Order> orderList = Order.dao.find("SELECT * FROM `order` where phone = ? ",phone);
		if(null != orderList && !orderList.isEmpty()){
			return orderList;
		}
		return null;
	}
	public String pay(String phone,String orderId,String payMoney){
		System.out.println("phone"+phone+"orderid"+orderId+"pay"+payMoney);
		Users users = Users.dao.findFirst("select * from users where phone = ?",phone);
		if(users.getMymoney() < Double.parseDouble(payMoney)){
			return "余额不足,请充值！";
		}
		users.setMymoney(users.getMymoney()-Double.parseDouble(payMoney));
		users.update();
		return Db.update("UPDATE `order` set status = ? where order_id = ?",2,orderId)>0?"":"支付失败！";		
	}
}
