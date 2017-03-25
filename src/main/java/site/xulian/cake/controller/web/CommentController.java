package site.xulian.cake.controller.web;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.JsonKit;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Record;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import site.xulian.cake.model.Comment;
import site.xulian.cake.utils.Constant;
import site.xulian.cake.utils.JsonType;

/**
 * @author Administrator
 *	获取评论的信息
 */
@ControllerBind(controllerKey="/comment")
public class CommentController extends Controller {
  Logger logger =Logger.getLogger(this.getClass());
  /**
   * 获取评论信息
   * @return
   * */
  public void getAllcomment(){
	  Integer shop_id = getParaToInt("shopId");
	  List<Record> commentList = Comment.dao.getAllcomment(shop_id);
	  if(null != commentList && !commentList.isEmpty()){
		  logger.info("评论信息"+JsonKit.toJson(commentList));
		  renderText(JsonType.toJson(Constant.CODE_SUCCESS, JsonKit.toJson(commentList)));
		  return;
	  }
	  renderText(JsonType.toJson(Constant.CODE_ERROR, ""));
  }
  /**
   * 获取评论信息
   * @return
   * */
  public void upload(){
	  String result = Comment.dao.saveComment(getPara("userId"),getPara("orderId"),getPara("context"));
	  if("".equals(result)){
		  renderText(JsonType.toJson(Constant.CODE_SUCCESS, "评论成功"));
		  return;
	  }
	  logger.info("评论信息"+result);
	  renderText(JsonType.toJson(Constant.CODE_ERROR, result));
  }
}
