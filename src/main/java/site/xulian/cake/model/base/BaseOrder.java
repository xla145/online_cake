package site.xulian.cake.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOrder<M extends BaseOrder<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setOrderId(java.lang.String orderId) {
		set("order_id", orderId);
	}

	public java.lang.String getOrderId() {
		return get("order_id");
	}

	public void setUserId(java.lang.Integer userId) {
		set("user_id", userId);
	}

	public java.lang.Integer getUserId() {
		return get("user_id");
	}

	public void setShopId(java.lang.Integer shopId) {
		set("shop_id", shopId);
	}

	public java.lang.Integer getShopId() {
		return get("shop_id");
	}

	public void setGoodsId(java.lang.Integer goodsId) {
		set("goods_id", goodsId);
	}

	public java.lang.Integer getGoodsId() {
		return get("goods_id");
	}

	public void setAddress(java.lang.String address) {
		set("address", address);
	}

	public java.lang.String getAddress() {
		return get("address");
	}

	public void setMoney(java.lang.Double money) {
		set("money", money);
	}

	public java.lang.Double getMoney() {
		return get("money");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}

	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}

	public java.lang.String getPhone() {
		return get("phone");
	}

	public void setGoodsName(java.lang.String goodsName) {
		set("goods_name", goodsName);
	}

	public java.lang.String getGoodsName() {
		return get("goods_name");
	}

}
