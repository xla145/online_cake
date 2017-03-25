package site.xulian.cake.base;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;

import site.xulian.cake.interceptor.AttrInter;
import site.xulian.cake.interceptor.LoginInter;

/**
 * API引导式配置
 */
public class Config extends JFinalConfig {

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("setting.properties");// 加载用户配置文件，获取值直接使用：PropKit.get(key值)
		me.setFreeMarkerTemplateUpdateDelay(0);
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		AutoBindRoutes routeBind = new AutoBindRoutes();
		routeBind.autoScan(false);
		me.add(routeBind);
	}

	// public static C3p0Plugin createC3p0Plugin() {
	// return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"),
	// PropKit.get("password").trim());
	// }

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置DruidPlugin数据库连接池插件
		DruidPlugin druidDefault = new DruidPlugin(PropKit.get("db.url"),
				PropKit.get("db.user"), PropKit.get("db.password"),
				PropKit.get("db.driver"));
		me.add(druidDefault);
		// Model自动绑定表插件
		AutoTableBindPlugin tableBindDefault = new AutoTableBindPlugin(
				druidDefault, SimpleNameStyles.LOWER);
		// tableBindDefault.setContainerFactory(new
		// CaseInsensitiveContainerFactory(false)); // 忽略字段大小写
		tableBindDefault.setShowSql(PropKit.getBoolean("devMode", false));
		// mysql的数据库方言
		tableBindDefault.setDialect(new MysqlDialect());
		tableBindDefault.autoScan(false);
		// _MappingKit.mapping(tableBindDefault);
		me.add(tableBindDefault);
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.add(new AttrInter());
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {

	}
}
