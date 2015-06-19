package com.zjl.myweather.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author oeaker@163.com:
 * @date 创建时间：2015-6-19 下午8:41:28
 * @version 1.0
 * @parameter
 * @since 生命不息，代码不止，做一个快乐的coder
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
