package com.wemall.order.entity;

import com.wemall.good.entity.Good;
import com.wemall.kill.entity.KillInfoDetail;

public class OrderDetail {
	private Integer id;
	private Order order;
	private Good good;
	private Integer num;
	private KillInfoDetail killInfoDetail;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public KillInfoDetail getKillInfoDetail() {
		return killInfoDetail;
	}
	public void setKillInfoDetail(KillInfoDetail killInfoDetail) {
		this.killInfoDetail = killInfoDetail;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

}
