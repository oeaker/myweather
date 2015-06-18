package com.zjl.myweather.model;
/** 
 * @author  oeaker@163.com: 
 * @date 创建时间：2015-6-18 下午9:05:15 
 * @version 1.0 
 * @parameter  
 * @since  生命不息，代码不止，做一个快乐的coder
 * @return  
 */
public class Province {
	
	private int id;
	
	private String provinceName;
	
	private String provinceCode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

}
