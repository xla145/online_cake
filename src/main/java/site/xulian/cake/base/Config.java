package site.xulian.cake.base;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.plugin.quartz.QuartzPlugin;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;

import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.template.Engine;
import site.xulian.cake.interceptor.AllowCrossDomain;
import site.xulian.cake.interceptor.AttrInter;
import site.xulian.cake.interceptor.LoginInter;
import site.xulian.cake.model._MappingKit;

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

	@Override
	public void configEngine(Engine me) {

	}
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置DruidPlugin数据库连接池插件
		DruidPlugin druidDefault = new DruidPlugin(PropKit.get("db.url"),
				PropKit.get("db.user"), PropKit.get("db.password"),
				PropKit.get("db.driver"));
		me.add(druidDefault);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidDefault);
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
		//添加定时器
		me.add(new QuartzPlugin("job.properties"));
		// 添加缓存
		me.add(new EhCachePlugin());
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.add(new AttrInter());
		// me.add(new AllowCrossDomain());
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {

	}
}
