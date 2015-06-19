package com.zjl.myweather;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.zjl.myweather.activity.WeatherActivity;
import com.zjl.myweather.util.LogUtil;
import com.zjl.myweather.util.MStrings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author oeaker@163.com:
 * @date ����ʱ�䣺2015-6-19 ����9:48:22
 * @version 1.0
 * @parameter
 * @since ������Ϣ�����벻ֹ����һ�����ֵ�coder
 * @return
 */
public class LocationActivity extends Activity {

	private LocationClient mLocationClient;
	private TextView LocationResult;
	private Button startLocation;
	private Button toweather;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.baidudemo_layout);

		LocationResult = (TextView) findViewById(R.id.dispaly_location_text);
		startLocation = (Button) findViewById(R.id.start);
		((LocationApplication) getApplication()).mLocationResult = LocationResult;

		try {
			mLocationClient = ((LocationApplication) getApplication()).mLocationClient;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LogUtil.e(MStrings.TAG, "��ʼ����ͼ��Ϣʧ��" + e.toString());
		}

		InitLocation(); // ��ʼ����λ��Ϣ
		startLocation.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				mLocationClient.start();
			}
		});

		toweather = (Button) findViewById(R.id.weather);
		toweather.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LocationActivity.this,
						WeatherActivity.class);
				intent.putExtra("county_code", "270101");
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	private void InitLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// ���ö�λģʽ
		option.setCoorType("gcj02");// ���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		int span = 1000;
		try {
			span = Integer.valueOf("1000");
		} catch (Exception e) {
			// TODO: handle exception
		}
		option.setScanSpan(span);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setIsNeedAddress(true); // �Ƿ���Ҫ��ַ��Ϣ
		mLocationClient.setLocOption(option);
	}
}
