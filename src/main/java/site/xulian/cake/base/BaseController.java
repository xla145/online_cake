package site.xulian.cake.base;

import com.jfinal.core.Controller;

public abstract class BaseController extends Controller{
	/**
	 * 返回操作状态，如果错误就全局处理，如果成功则不返回结果
	 * @param result
	 */
	public void renderJsonResult(boolean result){
		if (result){
			renderNull();
		}else{
			renderError(550);
		}
	}
	
	/**
	 * 返回错误码及对应的错误提示，可全局处理
	 * Render with view and errorCode status
	 */
	public void renderErrorText(String errorText) {
		int errorCode = 420;
		render(new ErrorTextRender(errorCode, errorText));
//		throw new ActionException(errorCode, new ErrorTextRender(errorCode, errorText));
	}
}
