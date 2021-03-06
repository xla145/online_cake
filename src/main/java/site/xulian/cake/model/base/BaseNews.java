package site.xulian.cake.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseNews<M extends BaseNews<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setTitle(java.lang.String title) {
		set("title", title);
	}

	public java.lang.String getTitle() {
		return get("title");
	}

	public void setType(java.lang.Integer type) {
		set("type", type);
	}

	public java.lang.Integer getType() {
		return get("type");
	}

	public void setAuthorName(java.lang.String authorName) {
		set("author_name", authorName);
	}

	public java.lang.String getAuthorName() {
		return get("author_name");
	}

	public void setUrl(java.lang.String url) {
		set("url", url);
	}

	public java.lang.String getUrl() {
		return get("url");
	}

	public void setThumbnailPicS(java.lang.String thumbnailPicS) {
		set("thumbnail_pic_s", thumbnailPicS);
	}

	public java.lang.String getThumbnailPicS() {
		return get("thumbnail_pic_s");
	}

	public void setUniquekey(java.lang.String uniquekey) {
		set("uniquekey", uniquekey);
	}

	public java.lang.String getUniquekey() {
		return get("uniquekey");
	}

	public void setNewsDate(java.util.Date newsDate) {
		set("news_date", newsDate);
	}

	public java.util.Date getNewsDate() {
		return get("news_date");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setUpadateTime(java.util.Date upadateTime) {
		set("upadate_time", upadateTime);
	}

	public java.util.Date getUpadateTime() {
		return get("upadate_time");
	}

}
