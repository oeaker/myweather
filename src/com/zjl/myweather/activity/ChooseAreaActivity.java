package com.zjl.myweather.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
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
 * @date ����ʱ�䣺2015-6-19 ����2:52:09
 * @version 1.0
 * @parameter
 * @since
 * @return ������Ϣ�����벻ֹ����һ�����ֵ�coder
 */
public class ChooseAreaActivity extends Activity {

	// �����г̼���
	private final static int PROVINCE_LEVEL = 0;

	private final static int CITY_LEVEL = 1;

	private final static int COUNTRY_LEVEL = 2;

	private ProgressDialog progressDialog;
	private TextView titleTextView;
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private CoolWeatherDB coolWeatherDB;

	private List<String> dataList = new ArrayList<String>();

	// ʡ�Ѕ^�б�
	private List<Province> provinceList;
	private List<City> cityList;
	private List<County> countryList;

	// ��ǰ�x�е�ʡ�Ѕ^
	private Province chooseProvince;
	private City chooseCity;
	private County chooseCounty;

	private int currentLevel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_area);

		listView = (ListView) findViewById(R.id.list_view);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dataList);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (currentLevel == CITY_LEVEL) {
					queryCity();
					chooseCity = cityList.get(position);
				} else if (currentLevel == COUNTRY_LEVEL) {
					queryCountry();
					chooseCounty = countryList.get(position);
				}
			}
		});
		
		queryProvince();
	}

	/**
	 * �������е�ʡ��Ϣ
	 */
	private void queryProvince() {
		provinceList = coolWeatherDB.loadProvinces();
		if (null != provinceList && provinceList.size() > 0) {
			dataList.clear();
			for (Province item : provinceList) {
				dataList.add(item.getProvinceName());
			}

			adapter.notifyDataSetChanged();
			titleTextView.setText("�й�");
			currentLevel = PROVINCE_LEVEL;
		} else {
			queryFromServer(null, "province");
			LogUtil.d(MStrings.TAG, "�������ȡʡ����Ϣ");
		}
	}

	/**
	 * ��ѯ���еĳ�����Ϣ
	 */
	private void queryCity() {
		cityList = coolWeatherDB.loadCities(chooseProvince.getId());
		if (null != cityList && cityList.size() > 0) {
			dataList.clear();
			for (City item : cityList) {
				dataList.add(item.getCityName());
			}

			adapter.notifyDataSetChanged();
			titleTextView.setText(chooseProvince.getProvinceName());
			currentLevel = CITY_LEVEL;
		} else {
			queryFromServer(String.valueOf(chooseProvince.getId()), "city");
			LogUtil.d(MStrings.TAG, "�������ȡ������Ϣ");
			
		}
	}

	/**
	 * ��ȡ���е������Ϣ
	 */
	private void queryCountry() {
		countryList = coolWeatherDB.loadCounties(chooseCity.getId());
		if (null != countryList && countryList.size() > 0) {
			dataList.clear();
			for (County item : countryList) {
				dataList.add(item.getCountyName());
			}

			adapter.notifyDataSetChanged();
			titleTextView.setText(chooseCity.getCityName());
			currentLevel = COUNTRY_LEVEL;
		}
		else {
			queryFromServer(String.valueOf(chooseCity.getId()), "county");
			LogUtil.d(MStrings.TAG, "�������ȡ������Ϣ");
		}
	}

	/**
	 * ���ݴ���Ĵ��ź����ʹӷ������ϲ�ѯʡ�������ݡ�
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
					// ͨ��runOnUiThread()�����ص����̴߳����߼�
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
				// ͨ��runOnUiThread()�����ص����̴߳����߼�
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						closeProgressDialog();
						Toast.makeText(ChooseAreaActivity.this, "����ʧ��",
								Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
	}

	/**
	 * չʾ��Ԓ��
	 */
	private void showProgressDialog() {
		if (null == progressDialog) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("����Ŭ���ļ��d��");
			progressDialog.setCanceledOnTouchOutside(false); // �O�ò���ȡ��
		}

		progressDialog.show();
	}

	/**
	 * �رնԻ���
	 */
	private void closeProgressDialog() {
		if (null != progressDialog) {
			progressDialog.dismiss();
		}
	}
}
