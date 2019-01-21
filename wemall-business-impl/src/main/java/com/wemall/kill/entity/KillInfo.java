package com.wemall.kill.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class KillInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String des;
	private Date begintime;
	private Date endtime;
	private List<KillInfoDetail> killInfoDetail;
	public List<KillInfoDetail> getKillInfoDetail() {
		return killInfoDetail;
	}
	public void setKillInfoDetail(List<KillInfoDetail> killInfoDetail) {
		this.killInfoDetail = killInfoDetail;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

}
