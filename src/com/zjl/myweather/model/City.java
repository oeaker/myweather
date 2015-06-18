package com.zjl.myweather.model;

/**
 * @author oeaker@163.com:
 * @date 创建时间：2015-6-18 下午9:06:54
 * @version 1.0
 * @parameter
 * @since 生命不息，代码不止，做一个快乐的coder
 * @return
 */
public class City {

	/**
	 * 城市ID
	 */
	private int id;

	/**
	 * 城市名称
	 */
	private String cityName;

	/**
	 * 城市代码
	 */
	private String cityCode;

	/**
	 * 省代码
	 */
	private int provinceId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

}
