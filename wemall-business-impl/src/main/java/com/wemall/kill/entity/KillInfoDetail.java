package com.wemall.kill.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.wemall.good.entity.Good;

public class KillInfoDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private KillInfo killInfo;
	private Good good;
	private BigDecimal price;
	private Integer num;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public KillInfo getKillInfo() {
		return killInfo;
	}
	public void setKillInfo(KillInfo killInfo) {
		this.killInfo = killInfo;
	}
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
}
