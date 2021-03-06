package site.xulian.cake.model;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;

import site.xulian.cake.model.base.BaseUsers;
import site.xulian.cake.utils.Common;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
@TableBind(tableName = "users")
public class Users extends BaseUsers<Users> {
	Logger logger = Logger.getLogger(this.getClass());
	public static final Users dao = new Users();
	/**
	 * 用户登录
	 * */
	public Users login(String phone,String password){
		logger.info("手机号："+phone);
		List<Users> userList = Users.dao.find("select * from users where phone = ? and password = ?",phone,password);
		if(null != userList && !userList.isEmpty()){
			return userList.get(0);
		}
		return null;
	}
	/**
	 * 用户注册
	 * */
	public boolean register(String phone,String password,String email,String nikename){
		logger.info("手机号："+phone);
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		sql.append("INSERT INTO users(phone,password,email,nikename,create_time,update_time) VALUES(?,?,?,?,now(),now())");
		list.add(phone);
		list.add(password);
		list.add(Common.nullToBlank(email));
		list.add(Common.nullToBlank(nikename));
		int num = Db.update(sql.toString(),list.toArray());
		if(num > 0){
			return true;
		}
		return false;
	}
}
