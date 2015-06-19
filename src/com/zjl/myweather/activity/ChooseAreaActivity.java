package com.zjl.myweather.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zjl.myweather.R;
import com.zjl.myweather.db.*;
import com.zjl.myweather.model.*;

/** 
 * @author  oeaker@163.com: 
 * @date 创建时间：2015-6-19 下午2:52:09 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return
 * 生命不息，代码不止，做一个快乐的coder
 */
public class ChooseAreaActivity extends Activity {
	
	
	// 定义行程级别
	private final static int PROVINCE_LEVEL = 0;
	
	private final static int CITY_LEVEL = 1;
	
	private final static int COUNTRY_LEVEL = 2;
	
	
	private ProgressDialog progressDialog ;
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
	private County chooseCounty;
	
	private int currentLevel;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_area);
		
		listView = (ListView) findViewById(R.id.list_view);
	}

}
