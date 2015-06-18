package com.zjl.myweather.model;
/** 
 * @author  oeaker@163.com: 
 * @date 创建时间：2015-6-18 下午9:09:12 
 * @version 1.0 
 * @parameter  
 * @since  生命不息，代码不止，做一个快乐的coder
 * @return  
 */
public class County {
	
	private int id;
	
	private String countryName;
	
	private String countryCode;
	
	private int cityId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

}
