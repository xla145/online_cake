package site.xulian.cake.controller.web;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import site.xulian.cake.model.News;
import site.xulian.cake.model.NewsType;
import site.xulian.cake.utils.Constant;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/13.
 */
@ControllerBind(controllerKey="/news")
public class NewsController extends Controller{
    /**
     * 获取新闻
     * */
    public void getData(){
        List<Map<String,Object>> newsList = News.dao.getNewsList(getParaToInt("pageSize", Constant.PAGE_SIZE),Constant.PAGE_NUM);
        renderJson(newsList);
    }
    /**
     * 获取新闻类型
     * */
    public void getNewsTypeList(){
        List<NewsType> newsTypeList = NewsType.dao.getNewsTypes();
        renderJson(newsTypeList);
    }
}
