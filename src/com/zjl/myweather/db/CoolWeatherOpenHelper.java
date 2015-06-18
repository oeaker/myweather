package com.zjl.myweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.wifi.WifiConfiguration.Status;

/**
 * @author oeaker@163.com:
 * @date 创建时间：2015-6-18 下午8:51:15
 * @version 1.0
 * @parameter
 * @since 生命不息，代码不止，做一个快乐的coder
 * @return
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

	/**
	 * 省的建表语句
	 */
	private static String CREATE_PROVINCE = "create table Province ("
			+ "id integer primary key autoincreament," + "province_name text"
			+ "province_code text)";

	/**
	 * 城市的建表语句
	 */
	private static String CREATE_CITY = "create table city ("
			+ "id integer primary key autoincreament" + "city_name text"
			+ "city_code text" + "province_id integer)";

	/**
	 * 城镇的建表语句
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
