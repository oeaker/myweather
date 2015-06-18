package com.zjl.myweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.wifi.WifiConfiguration.Status;

/**
 * @author oeaker@163.com:
 * @date ����ʱ�䣺2015-6-18 ����8:51:15
 * @version 1.0
 * @parameter
 * @since ������Ϣ�����벻ֹ����һ�����ֵ�coder
 * @return
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

	/**
	 * ʡ�Ľ������
	 */
	private static String CREATE_PROVINCE = "create table Province ("
			+ "id integer primary key autoincreament," + "province_name text"
			+ "province_code text)";

	/**
	 * ���еĽ������
	 */
	private static String CREATE_CITY = "create table city ("
			+ "id integer primary key autoincreament" + "city_name text"
			+ "city_code text" + "province_id integer)";

	/**
	 * ����Ľ������
	 */
	private static String CREATE_COUNTRY = "create table country("
			+ "id integer primary key autoincreament" + "country_name text"
			+ "country_code text" + "city_id integer)";

	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_COUNTRY);
		db.execSQL(CREATE_PROVINCE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}