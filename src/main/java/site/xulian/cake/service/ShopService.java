package site.xulian.cake.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import site.xulian.cake.model.Shop;

public class ShopService {
	public final static ShopService service = new ShopService();
	Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 获取所有店铺
	 * */
	public Page<Record> getAllShop(Integer pageNumber,Integer pageSize,String shopName,String shopPhone,Integer status){				
		logger.info("店铺信息:"+"pageNumber"+pageNumber+",pageSize="+pageSize+"shopName="+shopName+",shpPhone="+shopPhone+"status"+status);
		StringBuffer sql = new StringBuffer();
		List<Object> parmas =new ArrayList<Object>();
		sql.append(" FROM shop s");
		sql.append(" WHERE 1=1 AND s.status = 1");
		if(null != shopName && !"".equals(shopName)){
			sql.append(" AND s.name = ?");
			parmas.add(shopName);
		}
		if(null != shopPhone && !"".equals(shopPhone)){
			sql.append(" AND s.phone = ?");
			parmas.add(shopPhone);
		}
		if(null != status){
			sql.append(" AND s.status = ?");
			parmas.add(status);
		}
		sql.append(" ORDER BY s.update_time DESC");
		System.out.println("sql"+sql.toString());
		Page<Record> shopPage = Db.paginate(pageNumber, pageSize, "select * ", sql.toString(),parmas.toArray());
		if(shopPage.getList().size() == 0 && pageNumber > 1){
			return Db.paginate(pageNumber-1, pageSize, "select * ",sql.toString(),parmas.toArray());				
		}
		return shopPage;
	}
}
