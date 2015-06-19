package com.zjl.myweather.service;

import com.zjl.myweather.util.LogUtil;
import com.zjl.myweather.util.MStrings;
import com.zjl.myweather.util.Utility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

/**
 * @author oeaker@163.com:
 * @date 创建时间：2015-6-19 下午8:34:02
 * @version 1.0
 * @parameter
 * @since 生命不息，代码不止，做一个快乐的coder
 * @return
 */
public class AutoUpdateService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub

		LogUtil.d(MStrings.TAG, "进入更新天气的程序");

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				updateWeather();
			}
		}).start();

		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		int anhour = 10 * 1000; // 为了看效果，设置10S更新一次
		long targetTime = SystemClock.elapsedRealtime() + anhour;
		Intent i = new Intent(this, AutoUpdateService.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, i, 0);
		manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, targetTime,
				pendingIntent);
		return super.onStartCommand(intent, flags, startId);
	}

	private void updateWeather() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		String weatherCode = prefs.getString("weather_code", "");
		String address = "http://www.weather.com.cn/data/cityinfo/"
				+ weatherCode + ".html";
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
			@Override
			public void onFinish(String response) {
				Utility.handleWeatherResponse(AutoUpdateService.this, response);
			}

			@Override
			public void onError(Exception e) {
				e.printStackTrace();
			}
		});
	}
}
