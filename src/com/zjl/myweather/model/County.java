package com.zjl.myweather.model;
/** 
 * @author  oeaker@163.com: 
 * @date ����ʱ�䣺2015-6-18 ����9:09:12 
 * @version 1.0 
 * @parameter  
 * @since  ������Ϣ�����벻ֹ����һ�����ֵ�coder
 * @return  
 */
public class County {
	
	private int id;
	
	private String countyName;
	
	private String countyCode;
	
	private int cityId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
}
