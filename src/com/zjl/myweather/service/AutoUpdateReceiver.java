package com.zjl.myweather.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author oeaker@163.com:
 * @date ����ʱ�䣺2015-6-19 ����8:41:28
 * @version 1.0
 * @parameter
 * @since ������Ϣ�����벻ֹ����һ�����ֵ�coder
 * @return
 */
public class AutoUpdateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent i = new Intent(context, AutoUpdateService.class);
		context.startService(i);
	}
}
