package com.zjl.myweather.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zjl.myweather.R;
import com.zjl.myweather.db.*;
import com.zjl.myweather.model.*;
import com.zjl.myweather.util.*;
import com.zjl.myweather.service.*;

/**
 * @author oeaker@163.com:
 * @date 创建时间：2015-6-19 下午2:52:09
 * @version 1.0
 * @parameter
 * @since
 * @return 生命不息，代码不止，做一个快乐的coder
 */
public class ChooseAreaActivity extends Activity {

	// 定义行程级别
	private final static int PROVINCE_LEVEL = 0;

	private final static int CITY_LEVEL = 1;

	private final static int COUNTY_LEVEL = 2;

	private ProgressDialog progressDialog;
	private TextView titleTextView;
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private CoolWeatherDB coolWeatherDB;

	private List<String> dataList = new ArrayList<String>();

	// 省市區列表
	private List<Province> provinceList;
	private List<City> cityList;
	private List<County> countryList;

	// 當前選中的省市區
	private Province chooseProvince;
	private City chooseCity;

	private int currentLevel;

	/**
	 * 是否从WeatherActivity中跳转过来。
	 */
	private boolean isFromWeatherActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		isFromWeatherActivity = getIntent().getBooleanExtra(
				"from_weather_activity", false);

		if (prefs.getBoolean("city_selected", false) && !isFromWeatherActivity) {
			Intent intent = new Intent(this, WeatherActivity.class);
			startActivity(intent);
			finish();
			return;
		}

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_area);

		// 初始化数据库信息
		coolWeatherDB = new CoolWeatherDB(ChooseAreaActivity.this);

		titleTextView = (TextView) findViewById(R.id.title_text);
		listView = (ListView) findViewById(R.id.list_view);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dataList);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (currentLevel == PROVINCE_LEVEL) {
					chooseProvince = provinceList.get(position);
					queryCity();

				} else if (currentLevel == CITY_LEVEL) {
					chooseCity = cityList.get(position);
					queryCountry();
				} else if (currentLevel == COUNTY_LEVEL) {
					String countyCode = countryList.get(position)
							.getCountyCode();
					Intent intent = new Intent(ChooseAreaActivity.this,
							WeatherActivity.class);
					intent.putExtra("county_code", countyCode);
					startActivity(intent);
					finish();
				}

			}
		});

		queryProvince();
	}

	/**
	 * 加载所有的省信息
	 */
	private void queryProvince() {
		provinceList = coolWeatherDB.loadProvinces();
		if (null != provinceList && provinceList.size() > 0) {
			dataList.clear();
			for (Province item : provinceList) {
				dataList.add(item.getProvinceName());
			}

			adapter.notifyDataSetChanged();
			titleTextView.setText("中国");
			currentLevel = PROVINCE_LEVEL;
		} else {
			queryFromServer(null, "province");
			// LogUtil.d(MStrings.TAG, "从网络获取省份信息");
		}
	}

	/**
	 * 查询所有的城市信息
	 */
	private void queryCity() {
		cityList = coolWeatherDB.loadCities(Integer.valueOf(chooseProvince
				.getId()));
		if (null != cityList && cityList.size() > 0) {
			dataList.clear();
			for (City item : cityList) {
				dataList.add(item.getCityName());
			}

			adapter.notifyDataSetChanged();
			titleTextView.setText(chooseProvince.getProvinceName());
			currentLevel = CITY_LEVEL;
		} else {
			queryFromServer(String.valueOf(chooseProvince.getProvinceCode()),
					"city");
			// LogUtil.d(MStrings.TAG, "从网络获取城市信息");
		}
	}

	/**
	 * 获取所有的乡村信息
	 */
	private void queryCountry() {
		countryList = coolWeatherDB.loadCounties(Integer.valueOf(chooseCity
				.getId()));
		if (null != countryList && countryList.size() > 0) {
			dataList.clear();
			for (County item : countryList) {
				dataList.add(item.getCountyName());
			}

			adapter.notifyDataSetChanged();
			titleTextView.setText(chooseCity.getCityName());
			currentLevel = COUNTY_LEVEL;
		} else {
			queryFromServer(String.valueOf(chooseCity.getCityCode()), "county");
			// LogUtil.d(MStrings.TAG, "从网络获取城镇信息");
		}
	}

	/**
	 * 根据传入的代号和类型从服务器上查询省市县数据。
	 */
	private void queryFromServer(final String code, final String type) {
		String address;
		if (!TextUtils.isEmpty(code)) {
			address = "http://www.weather.com.cn/data/list3/city" + code
					+ ".xml";
		} else {
			address = "http://www.weather.com.cn/data/list3/city.xml";
		}
		showProgressDialog();
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
			@Override
			public void onFinish(String response) {
				boolean result = false;
				if ("province".equals(type)) {
					result = Utility.handleProvincesResponse(coolWeatherDB,
							response);
				} else if ("city".equals(type)) {
					result = Utility.handleCitiesResponse(coolWeatherDB,
							response, chooseProvince.getId());
				} else if ("county".equals(type)) {
					result = Utility.handleCountiesResponse(coolWeatherDB,
							response, chooseCity.getId());
				}
				if (result) {
					// 通过runOnUiThread()方法回到主线程处理逻辑
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							closeProgressDialog();
							if ("province".equals(type)) {
								queryProvince();
							} else if ("city".equals(type)) {
								queryCity();
							} else if ("county".equals(type)) {
								queryCountry();
							}
						}
					});
				}
			}

			@Override
			public void onError(Exception e) {
				// 打印错误信息
				LogUtil.e(MStrings.TAG, e.toString());
				// 通过runOnUiThread()方法回到主线程处理逻辑
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						closeProgressDialog();
						Toast.makeText(ChooseAreaActivity.this, "加载失败",
								Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
	}

	/**
	 * 展示對話框
	 */
	private void showProgressDialog() {
		if (null == progressDialog) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("正在努力的加載中");
			progressDialog.setCanceledOnTouchOutside(false); // 設置不可取消
		}

		progressDialog.show();
	}

	/**
	 * 关闭对话框
	 */
	private void closeProgressDialog() {
		if (null != progressDialog) {
			progressDialog.dismiss();
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (currentLevel == CITY_LEVEL) {
			queryProvince();
		} else if (currentLevel == COUNTY_LEVEL) {
			queryCity();
		} else {
			if (isFromWeatherActivity) {
				Intent intent = new Intent(this, WeatherActivity.class);
				startActivity(intent);
			}

			finish();
		}
	}
}
