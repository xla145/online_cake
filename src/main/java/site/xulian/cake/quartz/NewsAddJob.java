package site.xulian.cake.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import site.xulian.cake.model.News;
import site.xulian.cake.model.NewsType;
import site.xulian.cake.utils.RequestAPI;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */
public class NewsAddJob implements Job{
    final static Logger log = Logger.getLogger(NewsAddJob.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        /**
         * 获取所有新闻类型
         * */
        log.info("开始获取网上的新闻信息。。。。。。。。。。");
        List<NewsType> newsTypeList = NewsType.dao.getNewsTypes();
        for(NewsType type:newsTypeList) {
            String newsJson = RequestAPI.getNewsListByAPI(type.getEName());
            log.info("新闻信息"+newsJson);
            News.dao.saveNews(newsJson, type.getId());
        }
        log.info("结束获取网上的新闻信息。。。。。。。。。。");
    }
}
