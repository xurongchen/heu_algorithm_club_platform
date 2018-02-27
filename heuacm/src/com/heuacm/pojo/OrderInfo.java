package com.heuacm.pojo;

public class OrderInfo {
	private String ordernum;
	private boolean checked;
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String odernum) {
		this.ordernum = odernum;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "OrderInfo [ordernum=" + ordernum + ", checked=" + checked + "]";
	}
	
}
