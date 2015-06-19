package com.zjl.myweather.util;

import android.util.Log;

/** 
 * @author  oeaker@163.com: 
 * @date 创建时间：2015-6-19 下午3:36:14 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return
 * 生命不息，代码不止，做一个快乐的coder
 */
public class LogUtil {

	public static final int VERBOSE = 1;

	public static final int DEBUG = 2;

	public static final int INFO = 3;

	public static final int WARN = 4;

	public static final int ERROR = 5;

	public static final int NOTHING = 6;

	public static final int LEVEL = VERBOSE;

	public static void v(String tag, String msg) {
		if (LEVEL <= VERBOSE) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (LEVEL <= DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (LEVEL <= INFO) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (LEVEL <= WARN) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (LEVEL <= ERROR) {
			Log.e(tag, msg);
		}
	}
}
