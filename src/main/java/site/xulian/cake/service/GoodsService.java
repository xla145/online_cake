package site.xulian.cake.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


public class GoodsService {
	public final static GoodsService service = new GoodsService();
	Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 获取所有商品信息
	 * */
	public Page<Record> getAllGoods(Integer pageNumber,Integer pageSize,String goodsName,Integer goodsType){				
		logger.info("商品信息:"+"pageNumber"+pageNumber+",pageSize="+pageSize+"goodsName="+goodsName+",goodsType="+goodsType);
		StringBuffer sql = new StringBuffer();
		List<Object> parmas =new ArrayList<Object>();
		sql.append(" FROM goods g");
		sql.append(" WHERE 1=1 AND g.status = 1");
		if(null != goodsName && !"".equals(goodsName)){
			sql.append(" AND g.name = ?");
			parmas.add(goodsName);
		}
		if(null != goodsType){
			sql.append(" AND g.type = ?");
			parmas.add(goodsType);
		}
		sql.append(" ORDER BY g.update_time DESC");
		System.out.println("sql"+sql.toString());
		Page<Record> goodsPage = Db.paginate(pageNumber, pageSize, "select * ", sql.toString(),parmas.toArray());
		if(goodsPage.getList().size() == 0 && pageNumber > 1){
			return Db.paginate(pageNumber-1, pageSize, "select * ",sql.toString(),parmas.toArray());				
		}
		return goodsPage;
	}
}
