package com.wemall.order.entity;

import java.util.List;

public class Order {
	private Integer id;
	private String address;
	private Integer custom;
	private Integer isCommit;
	private Integer isPay;
	private Integer status;
	private List<OrderDetail> orderDetail;
	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getCustom() {
		return custom;
	}
	public void setCustom(Integer custom) {
		this.custom = custom;
	}
	public Integer getIsCommit() {
		return isCommit;
	}
	public void setIsCommit(Integer isCommit) {
		this.isCommit = isCommit;
	}
	public Integer getIsPay() {
		return isPay;
	}
	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
