package com.zjl.myweather.service;

/**
 * @author oeaker@163.com:
 * @date ����ʱ�䣺2015-6-18 ����9:36:30
 * @version 1.0
 * @parameter
 * @since ������Ϣ�����벻ֹ����һ�����ֵ�coder
 * @return
 */
public interface HttpCallbackListener {

	/**
	 * ִ�гɹ�֮��Ļص�����
	 */
	public void onFinish(String response);

	/**
	 * ִ��ʧ��֮��Ļص�����
	 */
	public void onError(Exception e);

}
