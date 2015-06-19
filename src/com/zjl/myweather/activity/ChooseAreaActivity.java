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
 * @date ����ʱ�䣺2015-6-19 ����2:52:09 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return
 * ������Ϣ�����벻ֹ����һ�����ֵ�coder
 */
public class ChooseAreaActivity extends Activity {
	
	
	// �����г̼���
	private final static int PROVINCE_LEVEL = 0;
	
	private final static int CITY_LEVEL = 1;
	
	private final static int COUNTRY_LEVEL = 2;
	
	
	private ProgressDialog progressDialog ;
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
	}

}
