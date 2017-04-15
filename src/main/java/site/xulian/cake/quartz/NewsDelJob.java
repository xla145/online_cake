package site.xulian.cake.quartz;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import site.xulian.cake.model.News;
import site.xulian.cake.model.NewsType;
import site.xulian.cake.utils.RequestAPI;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */
public class NewsDelJob implements Job{
    final static Logger log = Logger.getLogger(NewsDelJob.class);
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("开始删除新闻信息。。。。。。。。。。");
        News.dao.delNews(DateUtils.addDays(new Date(),-7));
        log.info("结束删除新闻信息。。。。。。。。。。");
    }
}
