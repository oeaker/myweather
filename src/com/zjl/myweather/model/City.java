package com.zjl.myweather.model;

/**
 * @author oeaker@163.com:
 * @date ����ʱ�䣺2015-6-18 ����9:06:54
 * @version 1.0
 * @parameter
 * @since ������Ϣ�����벻ֹ����һ�����ֵ�coder
 * @return
 */
public class City {

	/**
	 * ����ID
	 */
	private int id;

	/**
	 * ��������
	 */
	private String cityName;

	/**
	 * ���д���
	 */
	private String cityCode;

	/**
	 * ʡ����
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
