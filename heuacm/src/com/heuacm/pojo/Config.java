package com.heuacm.pojo;

public class Config {
	private String configkey;
	private String configval;
	public String getConfigkey() {
		return configkey;
	}
	public void setConfigkey(String configkey) {
		this.configkey = configkey;
	}
	public String getConfigval() {
		return configval;
	}
	public void setConfigval(String configval) {
		this.configval = configval;
	}
	@Override
	public String toString() {
		return "Config [configkey=" + configkey + ", configval=" + configval + "]";
	}
	
}
