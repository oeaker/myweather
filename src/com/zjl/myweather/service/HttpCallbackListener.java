package com.zjl.myweather.service;

/**
 * @author oeaker@163.com:
 * @date 创建时间：2015-6-18 下午9:36:30
 * @version 1.0
 * @parameter
 * @since 生命不息，代码不止，做一个快乐的coder
 * @return
 */
public interface HttpCallbackListener {

	/**
	 * 执行成功之后的回调函数
	 */
	public void onFinish(String response);

	/**
	 * 执行失败之后的回调函数
	 */
	public void onError(Exception e);

}
